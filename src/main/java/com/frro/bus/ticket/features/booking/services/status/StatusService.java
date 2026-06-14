package com.frro.bus.ticket.features.booking.services.status;

import org.springframework.data.domain.Pageable;

import com.frro.bus.ticket.common.dto.PageResponse;
import com.frro.bus.ticket.features.booking.dtos.TicketFullDTO;
import com.frro.bus.ticket.features.booking.dtos.SearchTicketDTO;

public interface StatusService {
    PageResponse<TicketFullDTO> findAllTickets(Pageable pageable);

    PageResponse<TicketFullDTO> searchTickets(SearchTicketDTO searchCriteria, Pageable pageable);

    TicketFullDTO findTicketById(int id);
}
