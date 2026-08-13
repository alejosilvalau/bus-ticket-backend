package com.frro.bus.ticket.features.journey.dtos.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateLocationDTO(
        @NotBlank @Size(min = 1, max = 100) String cityName,

        @NotBlank @Size(min = 1, max = 100) String state,

        @NotBlank @Size(min = 1, max = 20) String postalCode) {
}
