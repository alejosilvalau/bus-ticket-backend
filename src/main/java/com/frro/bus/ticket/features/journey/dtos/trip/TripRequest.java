package com.frro.bus.ticket.trip.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TripRequest(
    Integer idBus,
    Integer idDriver,
    Integer idLocationOrigin,
    Integer idLocationDestination,
    LocalDateTime departureDate,
    LocalDateTime arrivalDate,
    BigDecimal basePrice
) {}
