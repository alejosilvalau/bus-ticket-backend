package com.frro.bus.ticket.features.fleet.dtos.bus;

import java.util.Optional;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record SearchBusDTO(
        Optional<@Size(min = 1, max = 20) String> plateNumber,
        Optional<@Min(1) Integer> startTotalCapacity,
        Optional<@Min(1) Integer> endTotalCapacity,
        Optional<Boolean> isActive) {
}
