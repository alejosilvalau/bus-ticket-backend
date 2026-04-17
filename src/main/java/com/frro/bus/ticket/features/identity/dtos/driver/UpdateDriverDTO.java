package com.frro.bus.ticket.features.identity.dtos.driver;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateDriverDTO(
        @NotBlank @NotNull int id,

        String firstName,

        String lastName,

        Boolean isActive,

        String licenseNumber,

        String phoneNumber) implements DriverContract {
}
