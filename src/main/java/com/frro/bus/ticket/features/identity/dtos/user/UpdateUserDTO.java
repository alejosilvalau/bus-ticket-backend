package com.frro.bus.ticket.features.identity.dtos.user;

import java.util.Optional;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserDTO(
        @NotBlank int id,

        Optional<String> firstName,

        Optional<String> lastName,

        Optional<Boolean> isActive,

        Optional<String> email,

        Optional<Boolean> isAdmin) {
}
