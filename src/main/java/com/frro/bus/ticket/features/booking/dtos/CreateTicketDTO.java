package com.frro.bus.ticket.features.booking.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateTicketDTO(
        @NotNull @NotEmpty int idUser,
        @NotNull @NotEmpty int idTrip,
        @NotNull @NotEmpty int idSeat,
        @NotNull @NotEmpty BigDecimal finalPrice,
        @NotNull @NotEmpty LocalDateTime bookingTime,
        @NotNull @NotEmpty boolean isCancelled,
        @NotNull @NotEmpty String token) {
}
