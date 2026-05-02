package com.frro.bus.ticket.features.journey.dtos.trip;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

public record SearchTripDTO(
        Optional<ZonedDateTime> departureDate,
        Optional<ZonedDateTime> arrivalDate,
        Optional<BigDecimal> basePrice
// @NotBlank @NotNull int idBus,
// @NotBlank @NotNull int idDriverId,
// @NotBlank @NotNull int idLocationOrigin,
// @NotBlank @NotNull int idLocationDestination
) {
}
