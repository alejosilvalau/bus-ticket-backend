package com.frro.bus.ticket.features.booking.dtos;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record SearchTicketDTO(
        Optional<@DecimalMin(value = "0", message = "Start final price must be zero or positive") BigDecimal> startFinalPrice,
        Optional<@DecimalMin(value = "0", message = "End final price must be zero or positive") BigDecimal> endFinalPrice,
        Optional<ZonedDateTime> startBookingTime,
        Optional<ZonedDateTime> endBookingTime,
        Optional<Boolean> isCancelled,
        Optional<@Size(min = 1, max = 100, message = "Token must be between 1 and 100 characters") String> token,
        Optional<@Min(value = 1, message = "User ID must be a positive number") Integer> idUser,
        Optional<@Min(value = 1, message = "Trip ID must be a positive number") Integer> idTrip,
        Optional<@Min(value = 1, message = "Seat ID must be a positive number") Integer> idSeat) {
}
