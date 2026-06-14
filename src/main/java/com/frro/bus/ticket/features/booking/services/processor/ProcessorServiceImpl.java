package com.frro.bus.ticket.features.booking.services.processor;

import org.springframework.stereotype.Service;

import com.frro.bus.ticket.common.exceptions.BusinessException;
import com.frro.bus.ticket.common.exceptions.DuplicateResourceException;
import com.frro.bus.ticket.common.exceptions.ResourceNotFoundException;
import com.frro.bus.ticket.features.booking.dtos.CreateTicketDTO;
import com.frro.bus.ticket.features.booking.dtos.TicketFullDTO;
import com.frro.bus.ticket.features.booking.dtos.UpdateTicketDTO;
import com.frro.bus.ticket.features.booking.entities.Ticket;
import com.frro.bus.ticket.features.booking.mappers.TicketMapper;
import com.frro.bus.ticket.features.booking.repositories.TicketRepository;
import com.frro.bus.ticket.features.fleet.entities.Seat;
import com.frro.bus.ticket.features.identity.entities.User;
import com.frro.bus.ticket.features.journey.entities.Trip;
import com.frro.bus.ticket.features.journey.repositories.TripRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProcessorServiceImpl implements ProcessorService {
    private final TicketRepository ticketRepository;
    private final TripRepository tripRepository;
    private final TicketMapper ticketMapper;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public TicketFullDTO createTicket(CreateTicketDTO ticketRequest) {
        ticketRepository.findByTripIdAndSeatId(ticketRequest.tripId(), ticketRequest.seatId())
                .ifPresent(ticket -> {
                    throw new DuplicateResourceException("Ticket", "trip+seat combination",
                            "trip=" + ticketRequest.tripId() + ", seat=" + ticketRequest.seatId());
                });

        Trip trip = tripRepository.findById(ticketRequest.tripId())
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", ticketRequest.tripId()));

        long bookedSeats = ticketRepository.countByTripIdAndIsCancelledFalse(ticketRequest.tripId());
        if (bookedSeats >= trip.getBus().getTotalCapacity()) {
            throw new BusinessException("Trip is full. No available seats.");
        }

        Ticket ticket = ticketMapper.toTicket(ticketRequest);
        Ticket savedTicket = ticketRepository.save(ticket);
        entityManager.flush();
        entityManager.refresh(savedTicket);
        return ticketMapper.toTicketFullDTO(savedTicket);
    }

    @Override
    @Transactional
    public TicketFullDTO updateTicket(UpdateTicketDTO ticketRequest) {
        Ticket existingTicket = ticketRepository.findById(ticketRequest.id())
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", ticketRequest.id()));

        ticketRequest.finalPrice().ifPresent(existingTicket::setFinalPrice);
        ticketRequest.bookingTime().ifPresent(existingTicket::setBookingTime);
        ticketRequest.token().ifPresent(existingTicket::setToken);

        ticketRequest.userId().ifPresent(userId -> {
            User user = new User();
            user.setId(userId);
            existingTicket.setUser(user);
        });
        ticketRequest.tripId().ifPresent(tripId -> {
            Trip trip = new Trip();
            trip.setId(tripId);
            existingTicket.setTrip(trip);
        });
        ticketRequest.seatId().ifPresent(seatId -> {
            Seat seat = new Seat();
            seat.setId(seatId);
            existingTicket.setSeat(seat);
        });

        Ticket savedTicket = ticketRepository.save(existingTicket);
        entityManager.flush();
        entityManager.refresh(savedTicket);
        return ticketMapper.toTicketFullDTO(savedTicket);
    }

    @Override
    public TicketFullDTO deleteTicket(int id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", id));
        ticketRepository.deleteById(id);
        return ticketMapper.toTicketFullDTO(ticket);
    }
}
