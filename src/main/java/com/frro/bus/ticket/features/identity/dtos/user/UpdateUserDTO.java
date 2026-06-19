package com.frro.bus.ticket.features.identity.dtos.user;

import java.util.Optional;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateUserDTO(
        @NotNull(message = "User ID is required") @Min(value = 1, message = "User ID must be a positive number") Integer id,

        Optional<@Size(min = 1, max = 100, message = "First name must be between 1 and 100 characters") String> firstName,

        Optional<@Size(min = 1, max = 100, message = "Last name must be between 1 and 100 characters") String> lastName,

        Optional<@Email(message = "Email must be valid") String> email) {
}
