package com.frro.bus.ticket.features.identity.dtos.driver;

import com.frro.bus.ticket.common.config.LicenseNumberDeserializer;
import com.frro.bus.ticket.common.validations.ValidLicenseNumber;
import com.frro.bus.ticket.common.validations.ValidPhoneNumber;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import tools.jackson.databind.annotation.JsonDeserialize;

public record CreateDriverDTO(
        @NotBlank @Size(min = 1, max = 100) String firstName,

        @NotBlank @Size(min = 1, max = 100) String lastName,

        @NotNull Boolean isActive,

        @JsonDeserialize(using = LicenseNumberDeserializer.class)
        @NotBlank @Size(min = 1, max = 50) @ValidLicenseNumber String licenseNumber,

        @NotBlank @ValidPhoneNumber String phoneNumber) {
}
