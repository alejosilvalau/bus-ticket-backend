package com.frro.bus.ticket.features.booking.services.processor;

import org.springframework.stereotype.Service;

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

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProcessorServiceImpl implements ProcessorService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public TicketFullDTO createTicket(CreateTicketDTO ticketRequest) {
        ticketRepository.findByTripIdAndSeatId(ticketRequest.idTrip(), ticketRequest.idSeat())
                .ifPresent(ticket -> {
                    throw new DuplicateResourceException("Ticket", "trip+seat combination",
                            "trip=" + ticketRequest.idTrip() + ", seat=" + ticketRequest.idSeat());
                });

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
        ticketRequest.isCancelled().ifPresent(existingTicket::setCancelled);
        ticketRequest.token().ifPresent(existingTicket::setToken);

        ticketRequest.idUser().ifPresent(idUser -> {
            User user = new User();
            user.setId(idUser);
            existingTicket.setUser(user);
        });
        ticketRequest.idTrip().ifPresent(idTrip -> {
            Trip trip = new Trip();
            trip.setId(idTrip);
            existingTicket.setTrip(trip);
        });
        ticketRequest.idSeat().ifPresent(idSeat -> {
            Seat seat = new Seat();
            seat.setId(idSeat);
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
