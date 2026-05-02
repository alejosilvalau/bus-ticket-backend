package com.frro.bus.ticket.features.identity.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record UpdateUserDTO(
        @NotBlank @NotNull int id,

        String firstName,

        String lastName,

        Boolean isActive,

        String email,

        Boolean isAdmin) {
}
