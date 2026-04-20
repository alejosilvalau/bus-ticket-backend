package com.frro.bus.ticket.features.journey.dtos.trip;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TripDTO(
        @NotBlank @NotNull int id,
        @NotBlank @NotNull ZonedDateTime departureDate,
        @NotBlank @NotNull ZonedDateTime arrivalDate,
        @NotBlank @NotNull BigDecimal basePrice,
        @NotBlank @NotNull int idBus,
        @NotBlank @NotNull int idDriver,
        @NotBlank @NotNull int idLocationOrigin,
        @NotBlank @NotNull int idLocationDestination) {
}
