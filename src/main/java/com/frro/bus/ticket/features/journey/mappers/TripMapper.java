package com.frro.bus.ticket.features.journey.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frro.bus.ticket.common.utils.OptionalMapperUtil;
import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.CreateTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.SearchTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.UpdateTripDTO;
import com.frro.bus.ticket.features.journey.entities.Trip;

@Mapper(componentModel = "spring", uses = OptionalMapperUtil.class)
public interface TripMapper {
    TripDTO toTripDTO(Trip trip);

    @Mapping(target = "id", ignore = true)
    Trip toTrip(CreateTripDTO createTripDto);

    @Mapping(target = "departureDate", source = "departureDate", qualifiedByName = "unwrapOptionalZonedDateTime")
    @Mapping(target = "arrivalDate", source = "arrivalDate", qualifiedByName = "unwrapOptionalZonedDateTime")
    @Mapping(target = "basePrice", source = "basePrice", qualifiedByName = "unwrapOptionalBigDecimal")
    Trip toTrip(UpdateTripDTO updateTripDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "departureDate", source = "departureDate", qualifiedByName = "unwrapOptionalZonedDateTime")
    @Mapping(target = "arrivalDate", source = "arrivalDate", qualifiedByName = "unwrapOptionalZonedDateTime")
    @Mapping(target = "basePrice", source = "basePrice", qualifiedByName = "unwrapOptionalBigDecimal")
    Trip toTrip(SearchTripDTO searchTripDto);
}
