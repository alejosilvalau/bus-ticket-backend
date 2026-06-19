package com.frro.bus.ticket.features.identity.dtos.user;

import com.frro.bus.ticket.common.validations.StrongPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordUserDTO(
        @NotBlank(message = "Email is required") @Email(message = "Email must be valid") String email,

        @NotBlank(message = "Current password is required") String password,

        @NotBlank(message = "New password is required") @Size(min = 8, max = 100, message = "New password must be between 8 and 100 characters") @StrongPassword String newPassword) {
}
