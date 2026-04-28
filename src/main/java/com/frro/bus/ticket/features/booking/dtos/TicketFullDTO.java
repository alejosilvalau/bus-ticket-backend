package com.frro.bus.ticket.features.booking.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.frro.bus.ticket.features.fleet.entities.Seat;
import com.frro.bus.ticket.features.identity.entities.User;
import com.frro.bus.ticket.features.journey.entities.Trip;

public record TicketFullDTO(
        int id,
        User user,
        Trip trip,
        Seat seat,
        BigDecimal finalPrice,
        LocalDateTime bookingTime,
        boolean isCancelled,
        String token) {
}
