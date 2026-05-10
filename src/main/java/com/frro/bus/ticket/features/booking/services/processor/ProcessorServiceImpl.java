package com.frro.bus.ticket.features.booking.services.processor;

import java.util.Optional;

import org.springframework.stereotype.Service;

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
        Ticket ticket = ticketMapper.toTicket(ticketRequest);
        Ticket savedTicket = ticketRepository.save(ticket);
        entityManager.flush();
        entityManager.refresh(savedTicket);
        return ticketMapper.toTicketFullDTO(savedTicket);
    }

    @Override
    @Transactional
    public Optional<TicketFullDTO> updateTicket(UpdateTicketDTO ticketRequest) {
        return ticketRepository.findById(ticketRequest.id()).map(existingTicket -> {
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
        });
    }

    @Override
    public Optional<TicketFullDTO> deleteTicket(int id) {
        return ticketRepository.findById(id).map(ticket -> {
            ticketRepository.deleteById(id);
            return ticketMapper.toTicketFullDTO(ticket);
        });
    }
}
