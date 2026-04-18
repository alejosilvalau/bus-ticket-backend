package com.frro.bus.ticket.features.booking.entities.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketDTO(
    int id,
    int userId,
    int tripId,
    int seatId,
    BigDecimal finalPrice,
    LocalDateTime bookingTime,
    boolean isCancelled,
    String token
) {}
