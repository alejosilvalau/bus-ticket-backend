package com.frro.bus.ticket.features.journey.dtos.trip;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateTripDTO(
        @NotNull ZonedDateTime departureDate,

        @NotNull ZonedDateTime arrivalDate,

        @NotNull @DecimalMin("0") BigDecimal basePrice,

        @NotNull @Min(1) Integer busId,

        @NotNull @Min(1) Integer driverId,

        @NotNull @Min(1) Integer locationOriginId,

        @NotNull @Min(1) Integer locationDestinationId) {
}
