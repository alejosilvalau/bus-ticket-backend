package com.frro.bus.ticket.features.booking.services.processor;

import java.util.Optional;

import com.frro.bus.ticket.features.booking.dtos.CreateTicketDTO;
import com.frro.bus.ticket.features.booking.dtos.TicketDTO;
import com.frro.bus.ticket.features.booking.dtos.UpdateTicketDTO;

public interface ProcessorService {
    TicketDTO createTicket(CreateTicketDTO userRequest);

    Optional<TicketDTO> updateTicket(UpdateTicketDTO userRequest);

    Optional<TicketDTO> deleteTicket(int id);
}
