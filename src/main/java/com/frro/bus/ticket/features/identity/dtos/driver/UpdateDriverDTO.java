package com.frro.bus.ticket.features.identity.dtos.driver;

import java.util.Optional;

import com.frro.bus.ticket.common.validations.ValidPhoneNumber;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateDriverDTO(
        @NotNull(message = "Driver ID is required") @Min(value = 1, message = "Driver ID must be a positive number") Integer id,

        Optional<@Size(min = 1, max = 100, message = "First name must be between 1 and 100 characters") String> firstName,

        Optional<@Size(min = 1, max = 100, message = "Last name must be between 1 and 100 characters") String> lastName,

        Optional<Boolean> isActive,

        Optional<@Size(min = 1, max = 50, message = "License number must be between 1 and 50 characters") String> licenseNumber,

        Optional<@ValidPhoneNumber String> phoneNumber) {
}
