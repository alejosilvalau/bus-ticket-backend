package com.frro.bus.ticket.features.booking.dtos;

import java.util.Optional;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateTicketDTO(
        @NotNull @Min(1) Integer id,

        Optional<@Min(1) Integer> userId,

        Optional<@Min(1) Integer> tripId,

        Optional<@Min(1) Integer> seatId) {
}
