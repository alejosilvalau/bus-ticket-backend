package com.frro.bus.ticket.features.journey.dtos.location;

import java.util.Optional;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record UpdateLocationDTO(
        @Min(value = 1, message = "Location ID must be a positive number")
        int id,

        Optional<@Size(min = 1, max = 100, message = "City name must be between 1 and 100 characters") String> cityName,

        Optional<@Size(min = 1, max = 100, message = "State must be between 1 and 100 characters") String> state,

        Optional<@Size(min = 1, max = 20, message = "Postal code must be between 1 and 20 characters") String> postalCode) {
}
