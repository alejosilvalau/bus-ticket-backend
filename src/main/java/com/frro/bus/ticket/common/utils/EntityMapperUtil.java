package com.frro.bus.ticket.common.utils;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import com.frro.bus.ticket.features.fleet.entities.Bus;
import com.frro.bus.ticket.features.identity.entities.Driver;
import com.frro.bus.ticket.features.journey.entities.Location;

@Mapper(componentModel = "spring")
public interface EntityMapperUtil {
    // Entity to id mappers

    @Named("busToId")
    default Integer busToId(Bus bus) {
        return bus != null ? bus.getId() : null;
    }

    @Named("driverToId")
    default Integer driverToId(Driver driver) {
        return driver != null ? driver.getId() : null;
    }

    @Named("locationToId")
    default Integer locationToId(Location location) {
        return location != null ? location.getId() : null;
    }

    // Id to entity mappers

    @Named("idToBus")
    default Bus idToBus(Integer busId) {
        if (busId == null)
            return null;
        Bus bus = new Bus();
        bus.setId(busId);
        return bus;
    }

    @Named("idToDriver")
    default Driver idToDriver(Integer driverId) {
        if (driverId == null)
            return null;
        Driver driver = new Driver();
        driver.setId(driverId);
        return driver;
    }

    @Named("idToLocation")
    default Location idToLocation(Integer locationId) {
        if (locationId == null)
            return null;
        Location location = new Location();
        location.setId(locationId);
        return location;
    }

    // Optional Id to entity mappers

    @Named("optionalIdToBus")
    default Bus idToBusOptional(Optional<Integer> idBus) {
        return idBus.map(id -> {
            Bus bus = new Bus();
            bus.setId(id);
            return bus;
        }).orElse(null);
    }

    @Named("optionalIdToDriver")
    default Driver idToDriverOptional(Optional<Integer> idDriver) {
        return idDriver.map(id -> {
            Driver driver = new Driver();
            driver.setId(id);
            return driver;
        }).orElse(null);
    }

    @Named("optionalIdToLocation")
    default Location idToLocationOptional(Optional<Integer> idLocation) {
        return idLocation.map(id -> {
            Location location = new Location();
            location.setId(id);
            return location;
        }).orElse(null);
    }
}
