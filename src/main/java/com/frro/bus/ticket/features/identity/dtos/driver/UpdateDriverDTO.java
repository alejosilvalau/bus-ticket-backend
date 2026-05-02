package com.frro.bus.ticket.features.identity.dtos.driver;

import java.util.Optional;

import jakarta.validation.constraints.NotBlank;

public record UpdateDriverDTO(
        @NotBlank int id,

        Optional<String> firstName,

        Optional<String> lastName,

        Optional<Boolean> isActive,

        Optional<String> licenseNumber,

        Optional<String> phoneNumber) {
}
