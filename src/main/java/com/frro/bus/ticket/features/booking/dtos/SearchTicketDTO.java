package com.frro.bus.ticket.features.booking.dtos;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

public record SearchTicketDTO(
        Optional<BigDecimal> startFinalPrice,
        Optional<BigDecimal> endFinalPrice,
        Optional<ZonedDateTime> startBookingTime,
        Optional<ZonedDateTime> endBookingTime,
        Optional<Boolean> isCancelled,
        Optional<String> token,
        Optional<Integer> idUser,
        Optional<Integer> idTrip,
        Optional<Integer> idSeat) {
}
