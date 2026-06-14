package com.frro.bus.ticket.features.journey.dtos.location;

import java.util.Optional;

import jakarta.validation.constraints.Min;

public record UpdateLocationDTO(
        @Min(value = 1, message = "Location ID must be a positive number")
        int id,

        Optional<String> cityName,

        Optional<String> state,

        Optional<String> postalCode) {
}
