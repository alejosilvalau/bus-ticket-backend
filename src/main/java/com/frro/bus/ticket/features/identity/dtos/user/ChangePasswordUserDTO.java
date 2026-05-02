package com.frro.bus.ticket.features.identity.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChangePasswordUserDTO(
        @NotNull @Email @NotBlank String email,

        @NotNull @NotBlank String password,

        @NotNull @NotBlank String newPassword) {
}
