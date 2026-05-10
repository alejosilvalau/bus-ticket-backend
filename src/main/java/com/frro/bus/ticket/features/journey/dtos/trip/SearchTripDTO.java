package com.frro.bus.ticket.features.journey.dtos.trip;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

public record SearchTripDTO(
        Optional<ZonedDateTime> departureDate,
        Optional<ZonedDateTime> arrivalDate,
        Optional<BigDecimal> startBasePrice,
        Optional<BigDecimal> endBasePrice,
        Optional<Integer> idBus,
        Optional<Integer> idDriver,
        Optional<Integer> idLocationOrigin,
        Optional<Integer> idLocationDestination) {
}
