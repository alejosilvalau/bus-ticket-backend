package com.frro.bus.ticket.features.identity.dtos.driver;

import java.util.Optional;

import jakarta.validation.constraints.Size;

public record SearchDriverDTO(
        Optional<@Size(min = 1, max = 100, message = "First name must be between 1 and 100 characters") String> firstName,

        Optional<@Size(min = 1, max = 100, message = "Last name must be between 1 and 100 characters") String> lastName,

        Optional<Boolean> isActive,

        Optional<@Size(min = 1, max = 50, message = "License number must be between 1 and 50 characters") String> licenseNumber,

        Optional<@Size(min = 1, max = 20, message = "Phone number must be between 1 and 20 characters") String> phoneNumber) {
}
