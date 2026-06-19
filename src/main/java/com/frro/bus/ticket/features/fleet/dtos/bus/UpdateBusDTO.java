package com.frro.bus.ticket.features.fleet.dtos.bus;

import java.util.Optional;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateBusDTO(
        @NotNull @Min(1) Integer id,

        Optional<@Size(min = 1, max = 20) String> plateNumber,

        Optional<@Min(1) Integer> totalCapacity,

        Optional<Boolean> isActive) {
}
