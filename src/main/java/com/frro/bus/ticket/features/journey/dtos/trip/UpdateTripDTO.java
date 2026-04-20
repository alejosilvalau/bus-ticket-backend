package com.frro.bus.ticket.features.journey.dtos.trip;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTripDTO(
        @NotBlank @NotNull int id,
        ZonedDateTime departureDate,
        ZonedDateTime arrivalDate,
        BigDecimal basePrice,
        int idBus,
        int idDriver,
        int idLocationOrigin,
        int idLocationDestination) {
}
