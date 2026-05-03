package com.frro.bus.ticket.features.fleet.dtos.bus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BusDTO(
        @NotBlank int id,
        @NotBlank @NotNull String plateNumber,
        @NotBlank int totalCapacity,
        boolean isActive) {
}
