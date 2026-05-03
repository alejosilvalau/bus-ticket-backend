package com.frro.bus.ticket.features.booking.dtos;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import jakarta.validation.constraints.NotBlank;

public record UpdateTicketDTO(
        @NotBlank int id,
        // int idUser,
        // int idTrip,
        // int idSeat,
        Optional<BigDecimal> finalPrice,
        Optional<ZonedDateTime> bookingTime,
        Optional<Boolean> isCancelled,
        Optional<String> token) {
}
