package com.frro.bus.ticket.features.identity.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(
        @NotBlank @NotNull int id,

        String firstName,

        String lastName,

        Boolean isActive,

        String email,

        String password,

        Boolean isAdmin) implements UserContract {
}
