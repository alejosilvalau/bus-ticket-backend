package com.frro.bus.ticket.features.journey.dtos;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateTripDTO(
    Integer busId,
    Integer driverId,
    Integer locationOriginId,
    Integer locationDestinationId,
    LocalDateTime departureDate,
    LocalDateTime arrivalDate,
    BigDecimal basePrice
) {}
