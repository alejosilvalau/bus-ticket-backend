package com.frro.bus.ticket.features.identity.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDTO(
        @NotBlank @NotNull String firstName,

        @NotBlank @NotNull String lastName,

        @NotBlank @NotNull Boolean isActive,

        @NotBlank @NotNull @Email String email,

        @NotBlank @NotNull String password,

        @NotBlank @NotNull Boolean isAdmin) {
}
