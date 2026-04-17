package com.frro.bus.ticket.features.identity.dtos.driver;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DriverDTO(
        @NotBlank @NotNull int id,

        @NotBlank @NotNull String firstName,

        @NotBlank @NotNull String lastName,

        @NotBlank @NotNull Boolean isActive,

        @NotBlank @NotNull String licenseNumber,

        @NotBlank @NotNull String phoneNumber) implements DriverContract {
}
