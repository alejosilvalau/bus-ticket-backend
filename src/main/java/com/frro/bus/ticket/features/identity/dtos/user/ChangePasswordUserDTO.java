package com.frro.bus.ticket.features.identity.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ChangePasswordUserDTO(
        @NotBlank(message = "Email is required") @Email(message = "Email must be valid") String email,

        @NotBlank(message = "Current password is required") String password,

        @NotBlank(message = "New password is required") @Size(min = 8, max = 100, message = "New password must be between 8 and 100 characters") @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "New password must contain at least one uppercase letter, one lowercase letter, one number and one special character (@$!%*?&)") String newPassword) {
}
