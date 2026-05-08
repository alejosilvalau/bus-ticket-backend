package com.frro.bus.ticket.features.fleet.dtos.seat;

import java.util.Optional;

import jakarta.validation.constraints.NotBlank;

public record UpdateSeatDTO(
        @NotBlank int id,
        Optional<Character> letter,
        Optional<Integer> number,
        Optional<Boolean> isActive,
        Optional<Integer> idBus,
        Optional<Integer> idSeatType) {
}
