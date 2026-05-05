package com.frro.bus.ticket.common.utils.entities.ticket;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.frro.bus.ticket.common.utils.entities.EntityMapperDTOUtil;
import com.frro.bus.ticket.features.booking.dtos.TicketDTO;
import com.frro.bus.ticket.features.booking.entities.Ticket;
import com.frro.bus.ticket.features.booking.mappers.TicketMapper;

@Mapper(componentModel = "spring")
public abstract class TicketMapperDTOListUtil extends EntityMapperDTOUtil {

    @Autowired
    protected TicketMapper ticketMapper;

    @Named("ticketsToTicketDTOs")
    public List<TicketDTO> ticketsToTicketDTOs(List<Ticket> tickets) {
        return mapList(tickets, ticketMapper::toTicketDTO);
    }
}