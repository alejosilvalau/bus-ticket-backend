package com.frro.bus.ticket.features.identity.dtos.user;

import java.util.Optional;

import com.frro.bus.ticket.common.validations.ValidEmailProvider;
import com.frro.bus.ticket.common.validations.ValidName;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateUserDTO(
        @NotNull @Min(1) Integer id,

        Optional<@Size(min = 1, max = 100) @ValidName String> firstName,

        Optional<@Size(min = 1, max = 100) @ValidName String> lastName,

        Optional<@Email @ValidEmailProvider String> email) {
}
