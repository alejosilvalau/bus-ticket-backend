package com.frro.bus.ticket.features.journey.dtos.trip;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;

public record TripFullDTO(
        int id,
        ZonedDateTime departureDate,
        ZonedDateTime arrivalDate,
        BigDecimal basePrice,
        BusDTO bus,
        DriverDTO driver,
        LocationDTO locationOrigin,
        LocationDTO locationDestination) {
}
