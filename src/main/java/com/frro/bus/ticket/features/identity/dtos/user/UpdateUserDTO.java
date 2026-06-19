package com.frro.bus.ticket.features.identity.dtos.user;

import java.util.Optional;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateUserDTO(
        @NotNull @Min(1) Integer id,

        Optional<@Size(min = 1, max = 100) String> firstName,

        Optional<@Size(min = 1, max = 100) String> lastName,

        Optional<@Email String> email) {
}
