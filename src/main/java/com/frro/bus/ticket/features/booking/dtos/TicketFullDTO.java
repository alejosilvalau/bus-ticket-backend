package com.frro.bus.ticket.features.booking.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;
// import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;
// import com.frro.bus.ticket.features.fleet.dtos.seat.SeatDTO;

public record TicketFullDTO(
        int id,
        // UserDTO user,
        // TripDTO trip,
        // SeatDTO seat,
        BigDecimal finalPrice,
        LocalDateTime bookingTime,
        boolean isCancelled,
        String token) {
}
