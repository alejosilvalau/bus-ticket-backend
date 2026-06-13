package com.frro.bus.ticket.features.fleet.dtos.bus;

import java.util.Optional;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record UpdateBusDTO(
        @Min(value = 1, message = "Bus ID must be a positive number")
        int id,

        Optional<@Size(min = 1, max = 20, message = "Plate number must be between 1 and 20 characters") String> plateNumber,

        Optional<@Min(value = 1, message = "Total capacity must be at least 1") Integer> totalCapacity,

        Optional<Boolean> isActive) {
}
