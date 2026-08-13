package com.frro.bus.ticket.features.journey.dtos.location;

import java.util.Optional;

import jakarta.validation.constraints.Size;

public record SearchLocationDTO(
        Optional<@Size(min = 1, max = 100) String> cityName,
        Optional<@Size(min = 1, max = 100) String> state,
        Optional<@Size(min = 1, max = 20) String> postalCode) {
}
