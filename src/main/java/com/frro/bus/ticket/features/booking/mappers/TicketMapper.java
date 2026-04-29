package com.frro.bus.ticket.features.booking.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frro.bus.ticket.features.booking.dtos.CreateTicketDTO;
import com.frro.bus.ticket.features.booking.dtos.UpdateTicketDTO;
import com.frro.bus.ticket.features.booking.dtos.TicketDTO;
import com.frro.bus.ticket.features.booking.entities.Ticket;
import com.frro.bus.ticket.features.fleet.entities.Seat;
import com.frro.bus.ticket.features.identity.entities.User;
import com.frro.bus.ticket.features.journey.entities.Trip;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    @Mapping(target = "user", expression = "java(userFromId(ticketDto.idUser()))")
    @Mapping(target = "trip", expression = "java(tripFromId(ticketDto.idTrip()))")
    @Mapping(target = "seat", expression = "java(seatFromId(ticketDto.idSeat()))")
    Ticket toTicket(CreateTicketDTO ticketDto);

    @Mapping(target = "user", expression = "java(userFromId(ticketDto.idUser()))")
    @Mapping(target = "trip", expression = "java(tripFromId(ticketDto.idTrip()))")
    @Mapping(target = "seat", expression = "java(seatFromId(ticketDto.idSeat()))")
    Ticket toTicket(UpdateTicketDTO ticketDto);

    TicketDTO toTicketDTO(Ticket ticket);

    default User userFromId(int id) {
        User user = new User();
        user.setId(id);
        return user;
    }
    default Trip tripFromId(int id) {
        Trip trip = new Trip();
        trip.setId(id);
        return trip;
    }
    default Seat seatFromId(int id) {
        Seat seat = new Seat();
        seat.setId(id);
        return seat;
    }
}