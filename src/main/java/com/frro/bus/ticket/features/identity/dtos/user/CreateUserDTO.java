package com.frro.bus.ticket.features.identity.dtos.user;

import com.frro.bus.ticket.common.validations.StrongPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(
        @NotBlank @Size(min = 1, max = 100) String firstName,

        @NotBlank @Size(min = 1, max = 100) String lastName,

        @NotBlank @Email String email,

        @NotBlank @Size(min = 8, max = 100) @StrongPassword String password) {
}
