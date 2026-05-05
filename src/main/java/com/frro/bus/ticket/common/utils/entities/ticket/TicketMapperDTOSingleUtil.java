package com.frro.bus.ticket.common.utils.entities.ticket;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.frro.bus.ticket.common.utils.EntityMapperDTOUtil;
import com.frro.bus.ticket.features.booking.dtos.TicketDTO;
import com.frro.bus.ticket.features.booking.entities.Ticket;
import com.frro.bus.ticket.features.booking.mappers.TicketMapper;

@Mapper(componentModel = "spring")
public abstract class TicketMapperDTOSingleUtil extends EntityMapperDTOUtil {
    @Autowired
    protected TicketMapper ticketMapper;

    @Named("ticketToTicketDTO")
    public TicketDTO ticketToTicketDTO(Ticket ticket) {
        return mapSingle(ticket, ticketMapper::toTicketDTO);
    }
}
