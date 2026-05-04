package com.frro.bus.ticket.common.utils.entities;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.frro.bus.ticket.common.utils.EntityMapperDTOUtil;
import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;
import com.frro.bus.ticket.features.journey.entities.Trip;
import com.frro.bus.ticket.features.journey.mappers.TripMapper;

@Mapper(componentModel = "spring")
public abstract class TripMapperDTOUtil extends EntityMapperDTOUtil {

    @Autowired
    protected TripMapper tripMapper;

    @Named("tripToTripDTO")
    public TripDTO tripToTripDTO(Trip trip) {
        return mapSingle(trip, tripMapper::toTripDTO);
    }

    @Named("tripsToTripDTOs")
    public List<TripDTO> tripsToTripDTOs(List<Trip> trips) {
        return mapList(trips, tripMapper::toTripDTO);
    }

}
