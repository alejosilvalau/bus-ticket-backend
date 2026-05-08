package com.frro.bus.ticket.common.utils.entities.location;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.frro.bus.ticket.common.utils.entities.EntityMapperDTOUtils;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.entities.Location;
import com.frro.bus.ticket.features.journey.mappers.LocationMapper;

@Mapper(componentModel = "spring")
public abstract class LocationMapperDTOSingleUtil extends EntityMapperDTOUtils {

    @Autowired
    protected LocationMapper locationMapper;

    @Named("locationToLocationDTO")
    public LocationDTO locationToLocationDTO(Location location) {
        return mapSingle(location, locationMapper::toLocationDTO);
    }
}
