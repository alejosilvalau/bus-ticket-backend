package com.frro.bus.ticket.features.booking.entities.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdateTicketDTO(
    int id,
    Integer userId,
    Integer tripId,
    Integer seatId,
    BigDecimal finalPrice,
    LocalDateTime bookingTime,
    Boolean isCancelled,
    String token
) {}
