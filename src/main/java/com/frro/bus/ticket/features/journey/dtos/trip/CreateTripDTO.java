package com.frro.bus.ticket.features.journey.dtos.trip;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateTripDTO(
        @NotNull(message = "Departure date is required")
        ZonedDateTime departureDate,

        @NotNull(message = "Arrival date is required")
        ZonedDateTime arrivalDate,

        @NotNull(message = "Base price is required")
        @DecimalMin(value = "0", message = "Base price must be zero or positive")
        BigDecimal basePrice,

        @NotNull(message = "Bus ID is required")
        @Min(value = 1, message = "Bus ID must be a positive number")
        int idBus,

        @NotNull(message = "Driver ID is required")
        @Min(value = 1, message = "Driver ID must be a positive number")
        int idDriver,

        @NotNull(message = "Origin location ID is required")
        @Min(value = 1, message = "Origin location ID must be a positive number")
        int idLocationOrigin,

        @NotNull(message = "Destination location ID is required")
        @Min(value = 1, message = "Destination location ID must be a positive number")
        int idLocationDestination) {
}
