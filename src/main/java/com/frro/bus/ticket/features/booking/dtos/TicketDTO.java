package com.frro.bus.ticket.features.booking.dtos;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record TicketDTO(
        int id,
        BigDecimal finalPrice,
        ZonedDateTime bookingTime,
        boolean isCancelled) {
}
