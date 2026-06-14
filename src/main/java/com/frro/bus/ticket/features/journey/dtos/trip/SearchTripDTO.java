package com.frro.bus.ticket.features.journey.dtos.trip;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

public record SearchTripDTO(
        Optional<ZonedDateTime> departureDate,
        Optional<ZonedDateTime> arrivalDate,
        Optional<@DecimalMin(value = "0", message = "Start base price must be zero or positive") BigDecimal> startBasePrice,
        Optional<@DecimalMin(value = "0", message = "End base price must be zero or positive") BigDecimal> endBasePrice,
        Optional<@Min(value = 1, message = "Bus ID must be a positive number") Integer> busId,
        Optional<@Min(value = 1, message = "Driver ID must be a positive number") Integer> driverId,
        Optional<@Min(value = 1, message = "Origin location ID must be a positive number") Integer> locationOriginId,
        Optional<@Min(value = 1, message = "Destination location ID must be a positive number") Integer> locationDestinationId) {
}
