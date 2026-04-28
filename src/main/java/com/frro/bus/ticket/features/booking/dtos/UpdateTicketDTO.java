package com.frro.bus.ticket.features.booking.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateTicketDTO(
        @NotNull @NotEmpty int id,
        int idUser,
        int idTrip,
        int idSeat,
        BigDecimal finalPrice,
        LocalDateTime bookingTime,
        boolean isCancelled,
        String token) {
}
