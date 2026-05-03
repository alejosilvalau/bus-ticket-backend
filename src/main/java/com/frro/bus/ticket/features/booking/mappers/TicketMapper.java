package com.frro.bus.ticket.features.booking.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frro.bus.ticket.common.utils.OptionalMapperUtil;
import com.frro.bus.ticket.features.booking.dtos.CreateTicketDTO;
import com.frro.bus.ticket.features.booking.dtos.TicketDTO;
import com.frro.bus.ticket.features.booking.dtos.UpdateTicketDTO;
import com.frro.bus.ticket.features.booking.entities.Ticket;

@Mapper(componentModel = "spring", uses = OptionalMapperUtil.class)
public interface TicketMapper {
    @Mapping(target = "isCancelled", source = "cancelled")
    TicketDTO toTicketDTO(Ticket ticket);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cancelled", source = "isCancelled")
    Ticket toTicket(CreateTicketDTO createTicketDto);

    @Mapping(target = "finalPrice", source = "finalPrice", qualifiedByName = "unwrapOptionalBigDecimal")
    @Mapping(target = "bookingTime", source = "bookingTime", qualifiedByName = "unwrapOptionalZonedDateTime")
    @Mapping(target = "cancelled", source = "isCancelled", qualifiedByName = "unwrapOptionalBoolean")
    @Mapping(target = "token", source = "token", qualifiedByName = "unwrapOptionalString")
    Ticket toTicket(UpdateTicketDTO updateTicketDto);
}
