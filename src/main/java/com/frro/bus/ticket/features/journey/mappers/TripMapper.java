package com.frro.bus.ticket.features.journey.mappers;

import org.mapstruct.Mapper;

import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.CreateTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.SearchTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.UpdateTripDTO;
import com.frro.bus.ticket.features.journey.entities.Trip;

@Mapper(componentModel = "spring")
public interface TripMapper {
    TripDTO toTripDTO(Trip trip);

    Trip toTrip(CreateTripDTO createTripDto);

    Trip toTrip(UpdateTripDTO updateTripDto);

    Trip toTrip(SearchTripDTO searchTripDto);
}
