package com.frro.bus.ticket.features.booking.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frro.bus.ticket.common.utils.DataTypeMapperUtils;
import com.frro.bus.ticket.common.utils.entities.EntityMapperUtils;
import com.frro.bus.ticket.common.utils.entities.seat.SeatMapperDTOSingleUtil;
import com.frro.bus.ticket.common.utils.entities.trip.TripMapperDTOSingleUtil;
import com.frro.bus.ticket.common.utils.entities.user.UserMapperDTOSingleUtil;
import com.frro.bus.ticket.features.booking.dtos.CreateTicketDTO;
import com.frro.bus.ticket.features.booking.dtos.TicketDTO;
import com.frro.bus.ticket.features.booking.dtos.TicketFullDTO;
import com.frro.bus.ticket.features.booking.dtos.UpdateTicketDTO;
import com.frro.bus.ticket.features.booking.entities.Ticket;

@Mapper(componentModel = "spring", uses = { DataTypeMapperUtils.class, EntityMapperUtils.class,
        SeatMapperDTOSingleUtil.class,
        TripMapperDTOSingleUtil.class, UserMapperDTOSingleUtil.class })
public interface TicketMapper {
    @Mapping(target = "isCancelled", source = "cancelled")
    TicketDTO toTicketDTO(Ticket ticket);

    @Mapping(target = "isCancelled", source = "cancelled")
    @Mapping(target = "seat", source = "seat", qualifiedByName = "seatToSeatDTO")
    @Mapping(target = "trip", source = "trip", qualifiedByName = "tripToTripDTO")
    @Mapping(target = "user", source = "user", qualifiedByName = "userToUserDTO")
    TicketFullDTO toTicketFullDTO(Ticket ticket);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "finalPrice", ignore = true)
    @Mapping(target = "cancelled", ignore = true)
    @Mapping(target = "seat", source = "seatId", qualifiedByName = "idToSeat")
    @Mapping(target = "trip", source = "tripId", qualifiedByName = "idToTrip")
    @Mapping(target = "user", source = "userId", qualifiedByName = "idToUser")
    Ticket toTicket(CreateTicketDTO createTicketDto);

    @Mapping(target = "finalPrice", source = "finalPrice", qualifiedByName = "unwrapOptionalBigDecimal")
    @Mapping(target = "bookingTime", source = "bookingTime", qualifiedByName = "unwrapOptionalZonedDateTime")
    @Mapping(target = "token", source = "token", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "cancelled", ignore = true)
    @Mapping(target = "seat", source = "seatId", qualifiedByName = "optionalIdToSeat")
    @Mapping(target = "trip", source = "tripId", qualifiedByName = "optionalIdToTrip")
    @Mapping(target = "user", source = "userId", qualifiedByName = "optionalIdToUser")
    Ticket toTicket(UpdateTicketDTO updateTicketDto);
}
