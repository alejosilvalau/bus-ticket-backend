package com.frro.bus.ticket.common.utils;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;
import com.frro.bus.ticket.features.journey.entities.Trip;
import com.frro.bus.ticket.features.journey.mappers.TripMapper;

@Mapper(componentModel = "spring")
public abstract class EntityMapperDTOUtil {
    @Autowired
    protected TripMapper tripMapper;

    protected <T, D> List<D> mapList(List<T> entities, java.util.function.Function<T, D> mapper) {
        return entities != null ? entities.stream().map(mapper).toList() : List.of();
    }

    @Named("tripsToTripDTOs")
    public List<TripDTO> tripsToTripDTOs(List<Trip> trips) {
        return mapList(trips, tripMapper::toTripDTO);
    }
}
