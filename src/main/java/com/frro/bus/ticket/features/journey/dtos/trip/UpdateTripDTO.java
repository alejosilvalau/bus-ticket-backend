package com.frro.bus.ticket.features.journey.dtos.trip;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateTripDTO(
        @NotNull @Min(1) Integer id,

        Optional<ZonedDateTime> departureDate,

        Optional<ZonedDateTime> arrivalDate,

        Optional<@DecimalMin("0") BigDecimal> basePrice,

        Optional<@Min(1) Integer> busId,

        Optional<@Min(1) Integer> driverId,

        Optional<@Min(1) Integer> locationOriginId,

        Optional<@Min(1) Integer> locationDestinationId) {
}
