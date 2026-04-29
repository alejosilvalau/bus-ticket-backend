package com.frro.bus.ticket.features.journey.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frro.bus.ticket.features.fleet.entities.Bus;
import com.frro.bus.ticket.features.identity.entities.Driver;
import com.frro.bus.ticket.features.journey.dtos.trip.CreateTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripFullDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.UpdateTripDTO;
import com.frro.bus.ticket.features.journey.entities.Location;
import com.frro.bus.ticket.features.journey.entities.Trip;

@Mapper(componentModel = "spring")
public interface TripMapper {
    TripDTO toTripDTO(Trip trip);

    TripFullDTO toTripFullDTO(Trip trip);

    @Mapping(target = "bus", expression = "java(busFromId(tripDto.idBus()))")
    @Mapping(target = "driver", expression = "java(driverFromId(tripDto.idDriver()))")
    @Mapping(target = "locationOrigin", expression = "java(locationFromId(tripDto.idLocationOrigin()))")
    @Mapping(target = "locationDestination", expression = "java(locationFromId(tripDto.idLocationDestination()))")
    Trip toTrip(CreateTripDTO tripDto);

    @Mapping(target = "bus", expression = "java(busFromId(tripDto.idBus()))")
    @Mapping(target = "driver", expression = "java(driverFromId(tripDto.idDriver()))")
    @Mapping(target = "locationOrigin", expression = "java(locationFromId(tripDto.idLocationOrigin()))")
    @Mapping(target = "locationDestination", expression = "java(locationFromId(tripDto.idLocationDestination()))")
    Trip toTrip(UpdateTripDTO tripDto);

    default Bus busFromId(int id) {
        Bus bus = new Bus();
        bus.setId(id);
        return bus;
    }

    default Driver driverFromId(int id) {
        Driver driver = new Driver();
        driver.setId(id);
        return driver;
    }

    default Location locationFromId(int id) {
        Location location = new Location();
        location.setId(id);
        return location;
    }
}
