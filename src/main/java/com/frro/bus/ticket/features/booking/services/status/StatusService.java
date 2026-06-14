package com.frro.bus.ticket.features.booking.services.status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.frro.bus.ticket.features.booking.dtos.TicketFullDTO;
import com.frro.bus.ticket.features.booking.dtos.SearchTicketDTO;

public interface StatusService {
    Page<TicketFullDTO> findAllTickets(Pageable pageable);

    Page<TicketFullDTO> searchTickets(SearchTicketDTO searchCriteria, Pageable pageable);

    TicketFullDTO findTicketById(int id);
}
