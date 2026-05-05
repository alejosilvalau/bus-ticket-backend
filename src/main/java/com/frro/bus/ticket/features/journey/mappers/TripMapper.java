package com.frro.bus.ticket.features.journey.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frro.bus.ticket.common.utils.EntityMapperUtil;
import com.frro.bus.ticket.common.utils.OptionalMapperUtil;
import com.frro.bus.ticket.common.utils.entities.BusMapperDTOSingleUtil;
import com.frro.bus.ticket.common.utils.entities.DriverMapperDTOSingleUtil;
import com.frro.bus.ticket.common.utils.entities.LocationMapperDTOSingleUtil;
import com.frro.bus.ticket.common.utils.entities.TicketMapperDTOSingleUtil;
import com.frro.bus.ticket.common.utils.entities.TicketMapperDTOListUtil;
import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.*;
import com.frro.bus.ticket.features.journey.entities.Trip;

@Mapper(componentModel = "spring", uses = { OptionalMapperUtil.class, EntityMapperUtil.class,
        LocationMapperDTOSingleUtil.class, BusMapperDTOSingleUtil.class, DriverMapperDTOSingleUtil.class, TicketMapperDTOSingleUtil.class, TicketMapperDTOListUtil.class })
public interface TripMapper {
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
