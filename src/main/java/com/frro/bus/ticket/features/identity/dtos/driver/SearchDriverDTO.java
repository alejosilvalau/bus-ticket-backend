package com.frro.bus.ticket.features.identity.dtos.driver;

import java.util.Optional;

import com.frro.bus.ticket.common.validations.ValidLicenseNumber;
import com.frro.bus.ticket.common.validations.ValidName;
import com.frro.bus.ticket.common.validations.ValidPhoneNumber;

import jakarta.validation.constraints.Size;

public record SearchDriverDTO(
        Optional<@Size(min = 1, max = 100) @ValidName String> firstName,

        Optional<@Size(min = 1, max = 100) @ValidName String> lastName,

        Optional<Boolean> isActive,

        Optional<@Size(min = 1, max = 50) @ValidLicenseNumber String> licenseNumber,

        Optional<@Size(min = 1, max = 20) @ValidPhoneNumber String> phoneNumber) {
}
