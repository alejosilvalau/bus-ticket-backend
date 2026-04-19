package com.frro.bus.ticket.features.journey.dtos;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TripDTO(
    int id,
    int busId,
    int driverId,
    int locationOriginId,
    int locationDestinationId,
    LocalDateTime departureDate,
    LocalDateTime arrivalDate,
    BigDecimal basePrice
) {}
