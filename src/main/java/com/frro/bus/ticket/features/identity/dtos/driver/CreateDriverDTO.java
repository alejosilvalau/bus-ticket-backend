package com.frro.bus.ticket.features.identity.dtos.driver;

import com.frro.bus.ticket.common.validations.ValidPhoneNumber;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateDriverDTO(
        @NotBlank(message = "First name is required")
        @Size(min = 1, max = 100, message = "First name must be between 1 and 100 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(min = 1, max = 100, message = "Last name must be between 1 and 100 characters")
        String lastName,

        @NotNull(message = "Active status is required")
        Boolean isActive,

        @NotBlank(message = "License number is required")
        @Size(min = 1, max = 50, message = "License number must be between 1 and 50 characters")
        String licenseNumber,

        @NotBlank(message = "Phone number is required")
        @ValidPhoneNumber
        String phoneNumber) {
}
