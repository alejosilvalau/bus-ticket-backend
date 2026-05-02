package com.frro.bus.ticket.features.journey.dtos.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateLocationDTO(
        @NotBlank @NotNull String cityName,
        @NotBlank @NotNull String state,
        @NotBlank @NotNull String postalCode) {
}
