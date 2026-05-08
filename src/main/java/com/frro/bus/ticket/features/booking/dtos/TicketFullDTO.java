package com.frro.bus.ticket.features.booking.dtos;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.frro.bus.ticket.features.fleet.dtos.seat.SeatDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;

import jakarta.validation.constraints.NotEmpty;

public record TicketFullDTO(
        @NotEmpty int id,
        @NotEmpty BigDecimal finalPrice,
        @NotEmpty ZonedDateTime bookingTime,
        boolean isCancelled,
        @NotEmpty String token,
        @NotEmpty UserDTO user,
        @NotEmpty TripDTO trip,
        @NotEmpty SeatDTO seat) {
}
