package com.frro.bus.ticket.features.booking.services.status;

import java.util.List;
import java.util.Optional;

import com.frro.bus.ticket.features.booking.dtos.TicketFullDTO;

public interface StatusService {
    List<TicketFullDTO> findAllTickets();

    Optional<TicketFullDTO> findTicketById(int id);
}
