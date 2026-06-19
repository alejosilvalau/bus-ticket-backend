package com.frro.bus.ticket.features.identity.dtos.driver;

import java.util.Optional;

import com.frro.bus.ticket.common.validations.ValidPhoneNumber;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateDriverDTO(
        @NotNull @Min(1) Integer id,

        Optional<@Size(min = 1, max = 100) String> firstName,

        Optional<@Size(min = 1, max = 100) String> lastName,

        Optional<Boolean> isActive,

        Optional<@Size(min = 1, max = 50) String> licenseNumber,

        Optional<@ValidPhoneNumber String> phoneNumber) {
}
