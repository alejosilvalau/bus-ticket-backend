package com.frro.bus.ticket.features.booking.services.processor;

import com.frro.bus.ticket.features.booking.dtos.CreateTicketDTO;
import com.frro.bus.ticket.features.booking.dtos.TicketFullDTO;
import com.frro.bus.ticket.features.booking.dtos.UpdateTicketDTO;

public interface ProcessorService {
    TicketFullDTO createTicket(CreateTicketDTO userRequest);

    TicketFullDTO updateTicket(UpdateTicketDTO userRequest);

    TicketFullDTO deleteTicket(int id);
}
