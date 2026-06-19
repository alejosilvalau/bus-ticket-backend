package com.frro.bus.ticket.features.booking.dtos;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.frro.bus.ticket.features.fleet.dtos.seat.SeatDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;

public record TicketFullDTO(
        int id,
        BigDecimal finalPrice,
        ZonedDateTime bookingTime,
        boolean isCancelled,
        String token,
        UserDTO user,
        TripDTO trip,
        SeatDTO seat) {
}
