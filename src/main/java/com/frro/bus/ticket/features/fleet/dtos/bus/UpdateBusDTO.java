package com.frro.bus.ticket.features.fleet.dtos.bus;

import java.util.Optional;

import jakarta.validation.constraints.NotBlank;

public record UpdateBusDTO(
        @NotBlank int id,
        Optional<String> plateNumber,
        Optional<Integer> totalCapacity,
        Optional<Boolean> isActive) {
}
