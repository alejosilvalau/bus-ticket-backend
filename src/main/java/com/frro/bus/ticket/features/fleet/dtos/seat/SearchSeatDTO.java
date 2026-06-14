package com.frro.bus.ticket.features.fleet.dtos.seat;

import java.util.Optional;

import jakarta.validation.constraints.Min;

public record SearchSeatDTO(
        Optional<Character> letter,
        Optional<@Min(value = 1, message = "Seat number must be at least 1") Integer> number,
        Optional<Boolean> isActive,
        Optional<@Min(value = 1, message = "Bus ID must be a positive number") Integer> busId,
        Optional<@Min(value = 1, message = "Seat type ID must be a positive number") Integer> seatTypeId) {
}
