package com.frro.bus.ticket.features.journey.dtos.location;

import com.frro.bus.ticket.common.validations.ValidName;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateLocationDTO(
        @NotBlank @Size(min = 1, max = 100) @ValidName String cityName,

        @NotBlank @Size(min = 1, max = 100) @ValidName String state,

        @NotBlank @Size(min = 1, max = 20) String postalCode) {
}
