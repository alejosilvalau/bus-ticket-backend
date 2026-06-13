package com.frro.bus.ticket.features.fleet.dtos.bus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateBusDTO(
        @NotBlank(message = "Plate number is required")
        @Size(min = 1, max = 20, message = "Plate number must be between 1 and 20 characters")
        String plateNumber,

        @NotNull(message = "Total capacity is required")
        @Min(value = 1, message = "Total capacity must be at least 1")
        int totalCapacity,

        boolean isActive) {
}
