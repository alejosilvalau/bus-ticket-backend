package com.frro.bus.ticket.features.fleet.dtos.bus;

import java.util.Optional;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record SearchBusDTO(
        Optional<@Size(min = 1, max = 20, message = "Plate number must be between 1 and 20 characters") String> plateNumber,
        Optional<@Min(value = 1, message = "Start total capacity must be at least 1") Integer> startTotalCapacity,
        Optional<@Min(value = 1, message = "End total capacity must be at least 1") Integer> endTotalCapacity,
        Optional<Boolean> isActive) {
}
