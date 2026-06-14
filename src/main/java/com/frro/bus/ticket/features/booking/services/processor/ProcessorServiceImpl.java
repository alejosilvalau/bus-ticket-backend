package com.frro.bus.ticket.features.booking.services.processor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

import com.frro.bus.ticket.common.exceptions.BusinessException;
import com.frro.bus.ticket.common.exceptions.DuplicateResourceException;
import com.frro.bus.ticket.common.exceptions.ResourceNotFoundException;
import com.frro.bus.ticket.features.booking.dtos.CreateTicketDTO;
import com.frro.bus.ticket.features.booking.dtos.GetTicketFinalPriceDTO;
import com.frro.bus.ticket.features.booking.dtos.TicketFullDTO;
import com.frro.bus.ticket.features.booking.dtos.UpdateTicketDTO;
import com.frro.bus.ticket.features.booking.entities.Ticket;
import com.frro.bus.ticket.features.booking.mappers.TicketMapper;
import com.frro.bus.ticket.features.booking.repositories.TicketRepository;
import com.frro.bus.ticket.features.fleet.entities.Seat;
import com.frro.bus.ticket.features.fleet.repositories.SeatRepository;
import com.frro.bus.ticket.features.identity.entities.User;
import com.frro.bus.ticket.features.identity.repositories.UserRepository;
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
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final TicketMapper ticketMapper;
    private final PriceCalculationService priceCalculationService;
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

        Seat seat = seatRepository.findById(ticketRequest.seatId())
                .orElseThrow(() -> new ResourceNotFoundException("Seat", "id", ticketRequest.seatId()));

        long bookedSeats = ticketRepository.countByTripIdAndIsCancelledFalse(ticketRequest.tripId());
        if (bookedSeats >= trip.getBus().getTotalCapacity()) {
            throw new BusinessException("Trip is full. No available seats.");
        }

        Ticket ticket = ticketMapper.toTicket(ticketRequest);
        ticket.setBookingTime(ZonedDateTime.now());
        ticket.setFinalPrice(priceCalculationService.calculateFinalPriceValue(trip, seat));

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

        ticketRequest.userId().ifPresent(userId -> {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
            existingTicket.setUser(user);
        });

        Trip trip = existingTicket.getTrip();
        if (ticketRequest.tripId().isPresent()) {
            trip = tripRepository.findById(ticketRequest.tripId().get())
                    .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", ticketRequest.tripId().get()));
            existingTicket.setTrip(trip);
        }

        Seat seat = existingTicket.getSeat();
        if (ticketRequest.seatId().isPresent()) {
            seat = seatRepository.findById(ticketRequest.seatId().get())
                    .orElseThrow(() -> new ResourceNotFoundException("Seat", "id", ticketRequest.seatId().get()));
            existingTicket.setSeat(seat);
        }

        if (ticketRequest.tripId().isPresent() || ticketRequest.seatId().isPresent()) {
            existingTicket.setFinalPrice(priceCalculationService.calculateFinalPriceValue(trip, seat));
        }

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

    @Override
    @Transactional
    public TicketFullDTO cancelTicket(int id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", id));

        if (ticket.isCancelled()) {
            throw new BusinessException("Ticket is already cancelled.");
        }

        ticket.setCancelled(true);
        Ticket savedTicket = ticketRepository.save(ticket);
        entityManager.flush();
        entityManager.refresh(savedTicket);
        return ticketMapper.toTicketFullDTO(savedTicket);
    }

    @Override
    public BigDecimal getFinalPrice(GetTicketFinalPriceDTO request) {
        Trip trip = tripRepository.findById(request.tripId())
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", request.tripId()));

        Seat seat = seatRepository.findById(request.seatId())
                .orElseThrow(() -> new ResourceNotFoundException("Seat", "id", request.seatId()));

        return priceCalculationService.calculateFinalPriceValue(trip, seat);
    }
}
