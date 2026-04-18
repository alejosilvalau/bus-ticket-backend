package com.frro.bus.ticket.features.identity.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record UserDTO(
        @NotBlank @NotNull int id,

        @NotBlank @NotNull String firstName,

        @NotBlank @NotNull String lastName,

        @NotBlank @NotNull Boolean isActive,

        @NotBlank @NotNull String email,

        @Null String password,

        @NotBlank @NotNull Boolean isAdmin) implements UserContract {
}
