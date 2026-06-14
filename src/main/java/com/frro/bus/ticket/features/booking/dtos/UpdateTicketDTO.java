package com.frro.bus.ticket.features.booking.dtos;

import java.time.ZonedDateTime;
import java.util.Optional;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record UpdateTicketDTO(
        @Min(value = 1, message = "Ticket ID must be a positive number") int id,

        Optional<ZonedDateTime> bookingTime,

        Optional<@Size(min = 1, max = 100, message = "Token must be between 1 and 100 characters") String> token,

        Optional<@Min(value = 1, message = "User ID must be a positive number") Integer> userId,

        Optional<@Min(value = 1, message = "Trip ID must be a positive number") Integer> tripId,

        Optional<@Min(value = 1, message = "Seat ID must be a positive number") Integer> seatId) {
}
