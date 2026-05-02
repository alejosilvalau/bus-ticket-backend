package com.frro.bus.ticket.features.identity.dtos.driver;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateDriverDTO(
        @NotBlank @NotNull String firstName,

        @NotBlank @NotNull String lastName,

        @NotBlank boolean isActive,

        @NotBlank @NotNull String licenseNumber,

        @NotBlank @NotNull String phoneNumber) {
}
