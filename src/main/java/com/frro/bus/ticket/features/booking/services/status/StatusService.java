package com.frro.bus.ticket.features.booking.services.status;

import java.util.List;
import java.util.Optional;

import com.frro.bus.ticket.features.booking.dtos.TicketDTO;

public interface StatusService {
    List<TicketDTO> findAllTickets();

    Optional<TicketDTO> findTicketById(int id);
}
