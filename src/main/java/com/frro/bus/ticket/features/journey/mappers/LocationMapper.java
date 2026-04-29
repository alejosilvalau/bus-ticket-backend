package com.frro.bus.ticket.features.journey.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.UpdateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.CreateLocationDTO;
import com.frro.bus.ticket.features.journey.entities.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDTO toLocationDTO(Location location);

    @Mapping(expression = "createLocationDto.", target = "") // Id to entity
    Location toLocation(CreateLocationDTO createLocationDto);

    Location toLocation(UpdateLocationDTO updateLocationDto);
}
