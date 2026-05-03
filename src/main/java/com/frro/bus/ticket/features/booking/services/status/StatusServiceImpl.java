package com.frro.bus.ticket.features.booking.services.status;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.frro.bus.ticket.features.booking.dtos.TicketDTO;
import com.frro.bus.ticket.features.booking.mappers.TicketMapper;
import com.frro.bus.ticket.features.booking.repositories.TicketRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Override
    public List<TicketDTO> findAllTickets() {
        return ticketRepository.findAll().stream().map(ticketMapper::toTicketDTO).toList();
    }

    @Override
    public Optional<TicketDTO> findTicketById(int id) {
        return ticketRepository.findById(id).map(ticketMapper::toTicketDTO);
    }
}
