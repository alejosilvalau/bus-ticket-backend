package com.frro.bus.ticket.features.booking.dtos;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

public record SearchTicketDTO(
        Optional<BigDecimal> finalPrice,
        Optional<ZonedDateTime> bookingTime,
        Optional<Boolean> isCancelled,
        Optional<String> token,
        Optional<Integer> idUser,
        Optional<Integer> idTrip,
        Optional<Integer> idSeat) {
}
