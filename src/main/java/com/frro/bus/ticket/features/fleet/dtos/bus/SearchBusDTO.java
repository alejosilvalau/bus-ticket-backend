package com.frro.bus.ticket.features.fleet.dtos.bus;

import java.util.Optional;

public record SearchBusDTO(
        Optional<String> plateNumber,
        Optional<Integer> totalCapacity,
        Optional<Boolean> isActive) {
}
