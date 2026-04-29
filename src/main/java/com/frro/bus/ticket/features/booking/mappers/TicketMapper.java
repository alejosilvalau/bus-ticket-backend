package com.frro.bus.ticket.features.booking.mappers;

import org.mapstruct.Mapper;
import com.frro.bus.ticket.features.booking.dtos.TicketDTO;
import com.frro.bus.ticket.features.booking.entities.Ticket;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketDTO toTicketDTO(Ticket ticket);
}