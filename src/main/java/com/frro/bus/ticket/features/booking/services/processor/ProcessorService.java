package com.frro.bus.ticket.features.booking.services.processor;

import java.util.Optional;

import com.frro.bus.ticket.features.booking.dtos.CreateTicketDTO;
import com.frro.bus.ticket.features.booking.dtos.TicketFullDTO;
import com.frro.bus.ticket.features.booking.dtos.UpdateTicketDTO;

public interface ProcessorService {
    TicketFullDTO createTicket(CreateTicketDTO userRequest);

    Optional<TicketFullDTO> updateTicket(UpdateTicketDTO userRequest);

    Optional<TicketFullDTO> deleteTicket(int id);
}
