package com.frro.bus.ticket.features.booking.dtos;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateTicketDTO(
        // @NotNull @NotEmpty int idUser,
        // @NotNull @NotEmpty int idTrip,
        // @NotNull @NotEmpty int idSeat,
        @NotNull @NotEmpty BigDecimal finalPrice,
        @NotNull @NotEmpty ZonedDateTime bookingTime,
        boolean isCancelled,
        @NotNull @NotEmpty String token) {
}
