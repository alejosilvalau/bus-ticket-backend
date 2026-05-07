package com.frro.bus.ticket.features.identity.dtos.driver;

import java.util.Optional;

public record SearchDriverDTO(
        Optional<String> firstName,

        Optional<String> lastName,

        Optional<Boolean> isActive,

        Optional<String> licenseNumber,

        Optional<String> phoneNumber) {
}
