package com.frro.bus.ticket.features.journey.dtos.location;

import java.util.Optional;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateLocationDTO(
        @NotNull(message = "Location ID is required") @Min(value = 1, message = "Location ID must be a positive number") Integer id,

        Optional<String> cityName,

        Optional<String> state,

        Optional<String> postalCode) {
}
