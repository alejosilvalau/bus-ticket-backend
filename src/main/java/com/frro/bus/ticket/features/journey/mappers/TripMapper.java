package com.frro.bus.ticket.features.journey.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frro.bus.ticket.common.utils.entities.EntityMapperUtil;
import com.frro.bus.ticket.common.utils.DataTypeMapperUtil;
import com.frro.bus.ticket.common.utils.entities.bus.BusMapperDTOSingleUtil;
import com.frro.bus.ticket.common.utils.entities.driver.DriverMapperDTOSingleUtil;
import com.frro.bus.ticket.common.utils.entities.location.LocationMapperDTOSingleUtil;
import com.frro.bus.ticket.common.utils.entities.ticket.TicketMapperDTOSingleUtil;
import com.frro.bus.ticket.common.utils.entities.ticket.TicketMapperDTOListUtil;
import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.*;
import com.frro.bus.ticket.features.journey.entities.Trip;

@Mapper(componentModel = "spring", uses = { DataTypeMapperUtil.class, EntityMapperUtil.class,
        LocationMapperDTOSingleUtil.class, BusMapperDTOSingleUtil.class, DriverMapperDTOSingleUtil.class,
        TicketMapperDTOListUtil.class })
public interface TripMapper {
    @Mapping(target = "bus", ignore = true)
    @Mapping(target = "driver", ignore = true)
    @Mapping(target = "locationOrigin", ignore = true)
    @Mapping(target = "locationDestination", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    TripDTO toTripDTO(Trip trip);

    @Mapping(target = "bus", source = "bus", qualifiedByName = "busToBusDTO")
    @Mapping(target = "driver", source = "driver", qualifiedByName = "driverToDriverDTO")
    @Mapping(target = "locationOrigin", source = "locationOrigin", qualifiedByName = "locationToLocationDTO")
    @Mapping(target = "locationDestination", source = "locationDestination", qualifiedByName = "locationToLocationDTO")
    @Mapping(target = "tickets", source = "tickets", qualifiedByName = "ticketsToTicketDTOs")
    TripFullDTO toTripFullDTO(Trip trip);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bus", source = "idBus", qualifiedByName = "idToBus")
    @Mapping(target = "driver", source = "idDriver", qualifiedByName = "idToDriver")
    @Mapping(target = "locationOrigin", source = "idLocationOrigin", qualifiedByName = "idToLocation")
    @Mapping(target = "locationDestination", source = "idLocationDestination", qualifiedByName = "idToLocation")
    @Mapping(target = "tickets", ignore = true)
    Trip toTrip(CreateTripDTO createTripDto);

    @Mapping(target = "departureDate", source = "departureDate", qualifiedByName = "unwrapOptionalZonedDateTime")
    @Mapping(target = "arrivalDate", source = "arrivalDate", qualifiedByName = "unwrapOptionalZonedDateTime")
    @Mapping(target = "basePrice", source = "basePrice", qualifiedByName = "unwrapOptionalBigDecimal")
    @Mapping(target = "bus", source = "idBus", qualifiedByName = "optionalIdToBus")
    @Mapping(target = "driver", source = "idDriver", qualifiedByName = "optionalIdToDriver")
    @Mapping(target = "locationOrigin", source = "idLocationOrigin", qualifiedByName = "optionalIdToLocation")
    @Mapping(target = "locationDestination", source = "idLocationDestination", qualifiedByName = "optionalIdToLocation")
    @Mapping(target = "tickets", ignore = true)
    Trip toTrip(UpdateTripDTO updateTripDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "departureDate", source = "departureDate", qualifiedByName = "unwrapOptionalZonedDateTime")
    @Mapping(target = "arrivalDate", source = "arrivalDate", qualifiedByName = "unwrapOptionalZonedDateTime")
    @Mapping(target = "basePrice", source = "basePrice", qualifiedByName = "unwrapOptionalBigDecimal")
    @Mapping(target = "bus", source = "idBus", qualifiedByName = "optionalIdToBus")
    @Mapping(target = "driver", source = "idDriver", qualifiedByName = "optionalIdToDriver")
    @Mapping(target = "locationOrigin", source = "idLocationOrigin", qualifiedByName = "optionalIdToLocation")
    @Mapping(target = "locationDestination", source = "idLocationDestination", qualifiedByName = "optionalIdToLocation")
    @Mapping(target = "tickets", ignore = true)
    Trip toTrip(SearchTripDTO searchTripDto);
}
