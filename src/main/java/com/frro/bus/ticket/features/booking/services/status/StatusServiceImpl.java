package com.frro.bus.ticket.features.booking.services.status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.frro.bus.ticket.common.dto.PageResponse;
import com.frro.bus.ticket.common.exceptions.ResourceNotFoundException;
import com.frro.bus.ticket.features.booking.dtos.TicketFullDTO;
import com.frro.bus.ticket.features.booking.dtos.SearchTicketDTO;
import com.frro.bus.ticket.features.booking.mappers.TicketMapper;
import com.frro.bus.ticket.features.booking.repositories.TicketRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Override
    public PageResponse<TicketFullDTO> findAllTickets(Pageable pageable) {
        Page<TicketFullDTO> page = ticketRepository.findAll(pageable).map(ticketMapper::toTicketFullDTO);
        return toPageResponse(page);
    }

    @Override
    public PageResponse<TicketFullDTO> searchTickets(SearchTicketDTO searchCriteria, Pageable pageable) {
        Page<TicketFullDTO> page = ticketRepository.searchTickets(
                searchCriteria.startFinalPrice().orElse(null),
                searchCriteria.endFinalPrice().orElse(null),
                searchCriteria.startBookingTime().orElse(null),
                searchCriteria.endBookingTime().orElse(null),
                searchCriteria.isCancelled().orElse(null),
                searchCriteria.token().orElse(null),
                searchCriteria.userId().orElse(null),
                searchCriteria.tripId().orElse(null),
                searchCriteria.seatId().orElse(null),
                pageable)
                .map(ticketMapper::toTicketFullDTO);
        return toPageResponse(page);
    }

    @Override
    public TicketFullDTO findTicketById(int id) {
        return ticketRepository.findById(id)
                .map(ticketMapper::toTicketFullDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", id));
    }

    private <T> PageResponse<T> toPageResponse(Page<T> page) {
        return PageResponse.of(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.isEmpty());
    }
}
