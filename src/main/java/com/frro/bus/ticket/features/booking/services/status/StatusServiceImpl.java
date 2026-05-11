package com.frro.bus.ticket.features.booking.services.status;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
    public List<TicketFullDTO> findAllTickets() {
        return ticketRepository.findAll().stream().map(ticketMapper::toTicketFullDTO).toList();
    }

    @Override
    public List<TicketFullDTO> searchTickets(SearchTicketDTO searchCriteria) {
        return ticketRepository.searchTickets(
                searchCriteria.startFinalPrice().orElse(null),
                searchCriteria.endFinalPrice().orElse(null),
                searchCriteria.startBookingTime().orElse(null),
                searchCriteria.endBookingTime().orElse(null),
                searchCriteria.isCancelled().orElse(null),
                searchCriteria.token().orElse(null),
                searchCriteria.idUser().orElse(null),
                searchCriteria.idTrip().orElse(null),
                searchCriteria.idSeat().orElse(null))
                .stream()
                .map(ticketMapper::toTicketFullDTO)
                .toList();
    }

    @Override
    public Optional<TicketFullDTO> findTicketById(int id) {
        return ticketRepository.findById(id).map(ticketMapper::toTicketFullDTO);
    }

}
