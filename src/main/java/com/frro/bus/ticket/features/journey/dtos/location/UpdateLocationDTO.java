package com.frro.bus.ticket.features.journey.dtos.location;

import java.util.Optional;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateLocationDTO(
        @NotNull @Min(1) Integer id,

        Optional<String> cityName,

        Optional<String> state,

        Optional<String> postalCode) {
}
