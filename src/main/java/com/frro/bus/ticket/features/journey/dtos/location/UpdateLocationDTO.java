package com.frro.bus.ticket.features.journey.dtos.location;

import java.util.Optional;

import jakarta.validation.constraints.NotBlank;

public record UpdateLocationDTO(
        @NotBlank int id,
        Optional<String> cityName,
        Optional<String> state) {
}
