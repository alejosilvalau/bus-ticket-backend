package com.frro.bus.ticket.features.journey.dtos.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateLocationDTO(
        @NotBlank(message = "City name is required")
        @Size(min = 1, max = 100, message = "City name must be between 1 and 100 characters")
        String cityName,

        @NotBlank(message = "State is required")
        @Size(min = 1, max = 100, message = "State must be between 1 and 100 characters")
        String state,

        @NotBlank(message = "Postal code is required")
        @Size(min = 1, max = 20, message = "Postal code must be between 1 and 20 characters")
        String postalCode) {
}
