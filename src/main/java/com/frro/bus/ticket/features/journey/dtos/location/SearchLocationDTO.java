package com.frro.bus.ticket.features.journey.dtos.location;

import java.util.Optional;

import com.frro.bus.ticket.common.validations.ValidName;

import jakarta.validation.constraints.Size;

public record SearchLocationDTO(
        Optional<@Size(min = 1, max = 100) @ValidName String> cityName,
        Optional<@Size(min = 1, max = 100) @ValidName String> state,
        Optional<@Size(min = 1, max = 20) String> postalCode) {
}
