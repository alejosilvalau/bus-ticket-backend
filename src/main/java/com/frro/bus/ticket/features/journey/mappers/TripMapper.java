package com.frro.bus.ticket.features.journey.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frro.bus.ticket.common.utils.EntityMapperUtil;
import com.frro.bus.ticket.common.utils.OptionalMapperUtil;
import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.CreateTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.SearchTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.UpdateTripDTO;
import com.frro.bus.ticket.features.journey.entities.Trip;

@Mapper(componentModel = "spring", uses = { OptionalMapperUtil.class, EntityMapperUtil.class })
public interface TripMapper {
    @Mapping(target = "idBus", source = "bus", qualifiedByName = "busToId")
    @Mapping(target = "idDriver", source = "driver", qualifiedByName = "driverToId")
    @Mapping(target = "idLocationOrigin", source = "locationOrigin", qualifiedByName = "locationToId")
    @Mapping(target = "idLocationDestination", source = "locationDestination", qualifiedByName = "locationToId")
    TripDTO toTripDTO(Trip trip);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bus", source = "idBus", qualifiedByName = "idToBus")
    @Mapping(target = "driver", source = "idDriver", qualifiedByName = "idToDriver")
    @Mapping(target = "locationOrigin", source = "idLocationOrigin", qualifiedByName = "idToLocation")
    @Mapping(target = "locationDestination", source = "idLocationDestination", qualifiedByName = "idToLocation")
    Trip toTrip(CreateTripDTO createTripDto);

    @Mapping(target = "departureDate", source = "departureDate", qualifiedByName = "unwrapOptionalZonedDateTime")
    @Mapping(target = "arrivalDate", source = "arrivalDate", qualifiedByName = "unwrapOptionalZonedDateTime")
    @Mapping(target = "basePrice", source = "basePrice", qualifiedByName = "unwrapOptionalBigDecimal")
    @Mapping(target = "bus", source = "idBus", qualifiedByName = "optionalIdToBus")
    @Mapping(target = "driver", source = "idDriver", qualifiedByName = "optionalIdToDriver")
    @Mapping(target = "locationOrigin", source = "idLocationOrigin", qualifiedByName = "optionalIdToLocation")
    @Mapping(target = "locationDestination", source = "idLocationDestination", qualifiedByName = "optionalIdToLocation")
    Trip toTrip(UpdateTripDTO updateTripDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "departureDate", source = "departureDate", qualifiedByName = "unwrapOptionalZonedDateTime")
    @Mapping(target = "arrivalDate", source = "arrivalDate", qualifiedByName = "unwrapOptionalZonedDateTime")
    @Mapping(target = "basePrice", source = "basePrice", qualifiedByName = "unwrapOptionalBigDecimal")
    @Mapping(target = "bus", source = "idBus", qualifiedByName = "optionalIdToBus")
    @Mapping(target = "driver", source = "idDriver", qualifiedByName = "optionalIdToDriver")
    @Mapping(target = "locationOrigin", source = "idLocationOrigin", qualifiedByName = "optionalIdToLocation")
    @Mapping(target = "locationDestination", source = "idLocationDestination", qualifiedByName = "optionalIdToLocation")
    Trip toTrip(SearchTripDTO searchTripDto);
}
