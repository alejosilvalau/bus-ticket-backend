package com.frro.bus.ticket.features.journey.dtos.trip;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;

import jakarta.validation.constraints.NotBlank;

public record TripFullDTO(
        @NotBlank int id,
        @NotBlank ZonedDateTime departureDate,
        @NotBlank ZonedDateTime arrivalDate,
        @NotBlank BigDecimal basePrice,
        @NotBlank BusDTO bus,
        @NotBlank DriverDTO driver,
        @NotBlank LocationDTO locationOrigin,
        @NotBlank LocationDTO locationDestination) {
}
