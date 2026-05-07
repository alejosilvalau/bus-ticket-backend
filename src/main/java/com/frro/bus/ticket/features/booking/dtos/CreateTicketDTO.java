package com.frro.bus.ticket.features.booking.dtos;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotEmpty;

public record CreateTicketDTO(
        @NotEmpty BigDecimal finalPrice,
        @NotEmpty ZonedDateTime bookingTime,
        boolean isCancelled,
        @NotEmpty String token,
        @NotEmpty int idUser,
        @NotEmpty int idTrip,
        @NotEmpty int idSeat) {
}
