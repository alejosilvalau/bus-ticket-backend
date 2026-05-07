package com.frro.bus.ticket.features.booking.dtos;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.frro.bus.ticket.features.fleet.entities.Seat;
import com.frro.bus.ticket.features.identity.entities.User;
import com.frro.bus.ticket.features.journey.entities.Trip;

import jakarta.validation.constraints.NotEmpty;

public record TicketFullDTO(
        @NotEmpty int id,
        @NotEmpty BigDecimal finalPrice,
        @NotEmpty ZonedDateTime bookingTime,
        boolean isCancelled,
        @NotEmpty String token,
        @NotEmpty User user,
        @NotEmpty Trip trip,
        @NotEmpty Seat seat) {
}
