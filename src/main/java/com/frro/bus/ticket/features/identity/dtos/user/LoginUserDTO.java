package com.frro.bus.ticket.features.identity.dtos.user;

import com.frro.bus.ticket.common.validations.StrongPassword;
import com.frro.bus.ticket.common.validations.ValidEmailProvider;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginUserDTO(
        @NotBlank @Email @ValidEmailProvider String email,

        @NotBlank @Size(min = 8, max = 100) @StrongPassword String password) {
}
