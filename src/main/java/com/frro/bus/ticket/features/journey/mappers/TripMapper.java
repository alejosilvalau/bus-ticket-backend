package com.frro.bus.ticket.trip.mapper;

import com.frro.bus.ticket.trip.dto.TripRequest;
import com.frro.bus.ticket.trip.dto.TripResponse;
import com.frro.bus.ticket.trip.model.Trip;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TripMapper {
    TripResponse toTripResponse(Trip trip);
    Trip toTrip(TripRequest tripRequest);
}
