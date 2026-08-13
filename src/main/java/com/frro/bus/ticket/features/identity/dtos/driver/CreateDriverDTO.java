package com.frro.bus.ticket.features.identity.dtos.driver;

import com.frro.bus.ticket.common.validations.ValidPhoneNumber;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateDriverDTO(
        @NotBlank @Size(min = 1, max = 100) String firstName,

        @NotBlank @Size(min = 1, max = 100) String lastName,

        @NotNull Boolean isActive,

        @NotBlank @Size(min = 1, max = 50) String licenseNumber,

        @NotBlank @ValidPhoneNumber String phoneNumber) {
}
