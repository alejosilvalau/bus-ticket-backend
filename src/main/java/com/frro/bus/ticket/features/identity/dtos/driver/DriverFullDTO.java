package com.frro.bus.ticket.features.identity.dtos.driver;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record DriverFullDTO(
        @NotBlank int id,

        @NotBlank @NotNull String firstName,

        @NotBlank @NotNull String lastName,

        @NotBlank boolean isActive,

        @NotBlank @NotNull String licenseNumber,

        @NotBlank @NotNull String phoneNumber,

        List<Integer> idTrips) {
}
