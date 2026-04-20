package com.frro.bus.ticket.features.journey.dtos.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateLocationDTO(
        @NotBlank @NotNull int id,
        String cityName,
        String state) {
}
