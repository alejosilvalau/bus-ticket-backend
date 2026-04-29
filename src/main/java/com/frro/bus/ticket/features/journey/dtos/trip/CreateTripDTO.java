package com.frro.bus.ticket.features.journey.dtos.trip;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTripDTO(
        @NotBlank @NotNull ZonedDateTime departureDate,
        @NotBlank @NotNull ZonedDateTime arrivalDate,
        @NotBlank @NotNull BigDecimal basePrice
        // @NotBlank @NotNull int idBus,
        // @NotBlank @NotNull int idDriverId,
        // @NotBlank @NotNull int idLocationOrigin,
        // @NotBlank @NotNull int idLocationDestination
        ) {
}
