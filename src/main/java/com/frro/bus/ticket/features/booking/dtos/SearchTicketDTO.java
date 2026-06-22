package com.frro.bus.ticket.features.booking.dtos;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

public record SearchTicketDTO(
        Optional<@DecimalMin("0") BigDecimal> startFinalPrice,
        Optional<@DecimalMin("0") BigDecimal> endFinalPrice,
        Optional<ZonedDateTime> startBookingTime,
        Optional<ZonedDateTime> endBookingTime,
        Optional<Boolean> isCancelled,
        Optional<@Min(1) Integer> userId,
        Optional<@Min(1) Integer> tripId,
        Optional<@Min(1) Integer> seatId) {
}
