package com.frro.bus.ticket.features.booking.services.processor;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.frro.bus.ticket.features.booking.dtos.CreateTicketDTO;
import com.frro.bus.ticket.features.booking.dtos.TicketDTO;
import com.frro.bus.ticket.features.booking.dtos.UpdateTicketDTO;
import com.frro.bus.ticket.features.booking.entities.Ticket;
import com.frro.bus.ticket.features.booking.mappers.TicketMapper;
import com.frro.bus.ticket.features.booking.repositories.TicketRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProcessorServiceImpl implements ProcessorService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Override
    public TicketDTO createTicket(CreateTicketDTO ticketRequest) {
        Ticket ticket = ticketMapper.toTicket(ticketRequest);
        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.toTicketDTO(savedTicket);
    }

    @Override
    public Optional<TicketDTO> updateTicket(UpdateTicketDTO ticketRequest) {
        return ticketRepository.findById(ticketRequest.id()).map(existingTicket -> {
            ticketRequest.finalPrice().ifPresent(existingTicket::setFinalPrice);
            ticketRequest.bookingTime().ifPresent(existingTicket::setBookingTime);
            ticketRequest.isCancelled().ifPresent(existingTicket::setCancelled);
            ticketRequest.token().ifPresent(existingTicket::setToken);

            Ticket savedTicket = ticketRepository.save(existingTicket);
            return ticketMapper.toTicketDTO(savedTicket);
        });
    }

    @Override
    public Optional<TicketDTO> deleteTicket(int id) {
        return ticketRepository.findById(id).map(ticket -> {
            ticketRepository.deleteById(id);
            return ticketMapper.toTicketDTO(ticket);
        });
    }
}
