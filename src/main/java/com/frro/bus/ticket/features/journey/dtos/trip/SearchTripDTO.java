package com.frro.bus.ticket.features.journey.dtos.trip;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

public record SearchTripDTO(
        Optional<ZonedDateTime> startDepartureDate,
        Optional<ZonedDateTime> endDepartureDate,
        Optional<ZonedDateTime> startArrivalDate,
        Optional<ZonedDateTime> endArrivalDate,
        Optional<@DecimalMin("0") BigDecimal> startBasePrice,
        Optional<@DecimalMin("0") BigDecimal> endBasePrice,
        Optional<@Min(1) Integer> busId,
        Optional<@Min(1) Integer> driverId,
        Optional<@Min(1) Integer> locationOriginId,
        Optional<@Min(1) Integer> locationDestinationId,
        Optional<@Min(1) Integer> seatTypeId) {
}
