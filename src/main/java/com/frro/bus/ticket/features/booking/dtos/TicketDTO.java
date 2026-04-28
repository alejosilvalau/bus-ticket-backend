package com.frro.bus.ticket.features.booking.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketDTO(
        int id,
        int idUser,
        int idTrip,
        int idSeat,
        BigDecimal finalPrice,
        LocalDateTime bookingTime,
        boolean isCancelled,
        String token) {
}
