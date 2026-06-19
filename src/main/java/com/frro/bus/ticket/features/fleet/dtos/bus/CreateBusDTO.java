package com.frro.bus.ticket.features.fleet.dtos.bus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateBusDTO(
        @NotBlank @Size(min = 1, max = 20) String plateNumber,

        @NotNull @Min(1) Integer totalCapacity,

        @NotNull Boolean isActive) {
}
