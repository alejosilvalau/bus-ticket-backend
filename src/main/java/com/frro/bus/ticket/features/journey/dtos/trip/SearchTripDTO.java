package com.frro.bus.ticket.features.journey.dtos.trip;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record SearchTripDTO(
        ZonedDateTime departureDate,
        ZonedDateTime arrivalDate,
        BigDecimal basePrice
// @NotBlank @NotNull int idBus,
// @NotBlank @NotNull int idDriverId,
// @NotBlank @NotNull int idLocationOrigin,
// @NotBlank @NotNull int idLocationDestination
) {
}
