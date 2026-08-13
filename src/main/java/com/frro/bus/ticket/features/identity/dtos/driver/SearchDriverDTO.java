package com.frro.bus.ticket.features.identity.dtos.driver;

import java.util.Optional;

import jakarta.validation.constraints.Size;

public record SearchDriverDTO(
        Optional<@Size(min = 1, max = 100) String> firstName,

        Optional<@Size(min = 1, max = 100) String> lastName,

        Optional<Boolean> isActive,

        Optional<@Size(min = 1, max = 50) String> licenseNumber,

        Optional<@Size(min = 1, max = 20) String> phoneNumber) {
}
