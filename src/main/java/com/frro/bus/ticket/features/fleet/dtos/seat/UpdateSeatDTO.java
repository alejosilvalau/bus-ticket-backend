package com.frro.bus.ticket.features.fleet.dtos.seat;

import java.util.Optional;

import jakarta.validation.constraints.Min;

public record UpdateSeatDTO(
        @Min(value = 1, message = "Seat ID must be a positive number")
        int id,

        Optional<Character> letter,

        Optional<@Min(value = 1, message = "Seat number must be at least 1") Integer> number,

        Optional<Boolean> isActive,

        Optional<@Min(value = 1, message = "Bus ID must be a positive number") Integer> idBus,

        Optional<@Min(value = 1, message = "Seat type ID must be a positive number") Integer> idSeatType) {
}
