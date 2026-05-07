package com.frro.bus.ticket.features.fleet.dtos.seat;

import java.util.Optional;

public record SearchSeatDTO(
        Optional<Character> letter,
        Optional<Integer> number,
        Optional<Integer> idBus,
        Optional<Integer> idSeatType) {
}
