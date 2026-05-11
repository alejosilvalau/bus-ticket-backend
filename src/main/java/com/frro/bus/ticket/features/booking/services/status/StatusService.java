package com.frro.bus.ticket.features.booking.services.status;

import java.util.List;
import java.util.Optional;

import com.frro.bus.ticket.features.booking.dtos.TicketFullDTO;
import com.frro.bus.ticket.features.booking.dtos.SearchTicketDTO;

public interface StatusService {
    List<TicketFullDTO> findAllTickets();

    List<TicketFullDTO> searchTickets(SearchTicketDTO searchCriteria);

    Optional<TicketFullDTO> findTicketById(int id);
}
