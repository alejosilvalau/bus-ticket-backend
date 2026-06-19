package com.frro.bus.ticket.features.identity.dtos.user;

import com.frro.bus.ticket.common.validations.StrongPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(
        @NotBlank(message = "First name is required") @Size(min = 1, max = 100, message = "First name must be between 1 and 100 characters") String firstName,

        @NotBlank(message = "Last name is required") @Size(min = 1, max = 100, message = "Last name must be between 1 and 100 characters") String lastName,

        @NotBlank(message = "Email is required") @Email(message = "Email must be valid") String email,

        @NotBlank(message = "Password is required") @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters") @StrongPassword String password) {
}
