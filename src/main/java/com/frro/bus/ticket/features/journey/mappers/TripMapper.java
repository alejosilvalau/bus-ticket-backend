package com.frro.bus.ticket.features.journey.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frro.bus.ticket.common.utils.entities.EntityMapperUtils;
import com.frro.bus.ticket.common.utils.DataTypeMapperUtils;
import com.frro.bus.ticket.common.utils.entities.bus.BusMapperDTOSingleUtil;
import com.frro.bus.ticket.common.utils.entities.driver.DriverMapperDTOSingleUtil;
import com.frro.bus.ticket.common.utils.entities.location.LocationMapperDTOSingleUtil;
import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.*;
import com.frro.bus.ticket.features.journey.entities.Trip;

@Mapper(componentModel = "spring", uses = { DataTypeMapperUtils.class, EntityMapperUtils.class,
        LocationMapperDTOSingleUtil.class, BusMapperDTOSingleUtil.class, DriverMapperDTOSingleUtil.class })
public interface TripMapper {
    TripDTO toTripDTO(Trip trip);

    @Mapping(target = "bus", source = "bus", qualifiedByName = "busToBusDTO")
    @Mapping(target = "driver", source = "driver", qualifiedByName = "driverToDriverDTO")
    @Mapping(target = "locationOrigin", source = "locationOrigin", qualifiedByName = "locationToLocationDTO")
    @Mapping(target = "locationDestination", source = "locationDestination", qualifiedByName = "locationToLocationDTO")
    TripFullDTO toTripFullDTO(Trip trip);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bus", source = "busId", qualifiedByName = "idToBus")
    @Mapping(target = "driver", source = "driverId", qualifiedByName = "idToDriver")
    @Mapping(target = "locationOrigin", source = "locationOriginId", qualifiedByName = "idToLocation")
    @Mapping(target = "locationDestination", source = "locationDestinationId", qualifiedByName = "idToLocation")
    @Mapping(target = "tickets", ignore = true)
    Trip toTrip(CreateTripDTO createTripDto);

    @Mapping(target = "departureDate", source = "departureDate", qualifiedByName = "unwrapOptionalZonedDateTime")
    @Mapping(target = "arrivalDate", source = "arrivalDate", qualifiedByName = "unwrapOptionalZonedDateTime")
    @Mapping(target = "basePrice", source = "basePrice", qualifiedByName = "unwrapOptionalBigDecimal")
    @Mapping(target = "bus", source = "busId", qualifiedByName = "optionalIdToBus")
    @Mapping(target = "driver", source = "driverId", qualifiedByName = "optionalIdToDriver")
    @Mapping(target = "locationOrigin", source = "locationOriginId", qualifiedByName = "optionalIdToLocation")
    @Mapping(target = "locationDestination", source = "locationDestinationId", qualifiedByName = "optionalIdToLocation")
    @Mapping(target = "tickets", ignore = true)
    Trip toTrip(UpdateTripDTO updateTripDto);
}
