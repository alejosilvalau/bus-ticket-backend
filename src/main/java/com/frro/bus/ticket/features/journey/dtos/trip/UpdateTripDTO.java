package com.frro.bus.ticket.features.journey.dtos.trip;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UpdateTripDTO(
        @NotBlank(message = "ID is required") @Min(value = 1, message = "Trip ID must be a positive number") Integer id,

        Optional<ZonedDateTime> departureDate,

        Optional<ZonedDateTime> arrivalDate,

        Optional<@DecimalMin(value = "0", message = "Base price must be zero or positive") BigDecimal> basePrice,

        Optional<@Min(value = 1, message = "Bus ID must be a positive number") Integer> busId,

        Optional<@Min(value = 1, message = "Driver ID must be a positive number") Integer> driverId,

        Optional<@Min(value = 1, message = "Origin location ID must be a positive number") Integer> locationOriginId,

        Optional<@Min(value = 1, message = "Destination location ID must be a positive number") Integer> locationDestinationId) {
}
