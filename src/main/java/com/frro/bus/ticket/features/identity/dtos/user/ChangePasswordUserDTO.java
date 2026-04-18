package com.frro.bus.ticket.features.identity.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record ChangePasswordUserDTO(
        @Null int id,

        @Null String firstName,

        @Null String lastName,

        @Null Boolean isActive,

        @NotNull @Email @NotBlank String email,

        @NotNull @NotBlank String password,

        @NotNull @NotBlank String newPassword,

        @Null Boolean isAdmin) implements UserContract {
}
