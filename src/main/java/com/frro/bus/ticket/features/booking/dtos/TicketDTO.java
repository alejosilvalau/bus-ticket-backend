package com.frro.bus.ticket.features.booking.dtos;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotBlank;

public record TicketDTO(
        @NotBlank int id,
        // int idUser,
        // int idTrip,
        // int idSeat,
        @NotBlank BigDecimal finalPrice,
        @NotBlank ZonedDateTime bookingTime,
        boolean isCancelled,
        @NotBlank String token) {
}
