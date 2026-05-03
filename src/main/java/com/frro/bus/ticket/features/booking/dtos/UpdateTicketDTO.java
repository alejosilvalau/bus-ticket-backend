package com.frro.bus.ticket.features.booking.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateTicketDTO(
        @NotBlank int id,
        // int idUser,
        // int idTrip,
        // int idSeat,
        Optional<BigDecimal> finalPrice,
        Optional<LocalDateTime> bookingTime,
        Optional<Boolean> isCancelled,
        Optional<String> token) {
}
