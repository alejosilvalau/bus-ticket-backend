package com.frro.bus.ticket.features.fleet.dtos.seat;

import java.util.Optional;

import jakarta.validation.constraints.Min;

public record SearchSeatDTO(
        Optional<Character> letter,
        Optional<@Min(1) Integer> number,
        Optional<Boolean> isActive,
        Optional<@Min(1) Integer> busId,
        Optional<@Min(1) Integer> seatTypeId) {
}
