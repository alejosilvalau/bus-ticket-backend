package com.frro.bus.ticket.features.journey.dtos.trip;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record TripDTO(
        int id,
        ZonedDateTime departureDate,
        ZonedDateTime arrivalDate,
        BigDecimal basePrice) {
}
