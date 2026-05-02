package com.frro.bus.ticket.features.fleet.dtos.bus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateBusDTO(
        @NotBlank @NotNull int id,
        String plateNumber,
        int totalCapacity,
        boolean isActive) {
}
