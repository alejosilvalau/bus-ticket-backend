package com.frro.bus.ticket.features.booking.services.status;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.frro.bus.ticket.features.booking.dtos.TicketFullDTO;
import com.frro.bus.ticket.features.booking.mappers.TicketMapper;
import com.frro.bus.ticket.features.booking.repositories.TicketRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Override
    public List<TicketFullDTO> findAllTickets() {
        return ticketRepository.findAll().stream().map(ticketMapper::toTicketFullDTO).toList();
    }

    @Override
    public Optional<TicketFullDTO> findTicketById(int id) {
        return ticketRepository.findById(id).map(ticketMapper::toTicketFullDTO);
    }
}
