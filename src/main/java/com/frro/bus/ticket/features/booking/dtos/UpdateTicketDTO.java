package com.frro.bus.ticket.features.booking.dtos;

import java.util.Optional;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTicketDTO(
        @NotNull @Min(1) Integer id,

        Optional<@Size(min = 1, max = 100) String> token,

        Optional<@Min(1) Integer> userId,

        Optional<@Min(1) Integer> tripId,

        Optional<@Min(1) Integer> seatId) {
}
