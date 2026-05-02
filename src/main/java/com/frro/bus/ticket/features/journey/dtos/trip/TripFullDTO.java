package com.frro.bus.ticket.features.journey.dtos.trip;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

// import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
// import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
// import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TripFullDTO(
        @NotBlank int id,
        @NotBlank @NotNull ZonedDateTime departureDate,
        @NotBlank @NotNull ZonedDateTime arrivalDate,
        @NotBlank @NotNull BigDecimal basePrice
// @NotBlank @NotNull BusDTO bus,
// @NotBlank @NotNull DriverDTO driver,
// @NotBlank @NotNull LocationDTO locationOrigin,
// @NotBlank @NotNull LocationDTO locationDestination
) {
}
