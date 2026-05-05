package com.frro.bus.ticket.common.utils.entities;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.frro.bus.ticket.common.utils.EntityMapperDTOUtil;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.entities.Location;
import com.frro.bus.ticket.features.journey.mappers.LocationMapper;

@Mapper(componentModel = "spring")
public abstract class LocationMapperDTOListUtil extends EntityMapperDTOUtil {

    @Autowired
    protected LocationMapper locationMapper;

    @Named("locationsToLocationDTOs")
    public List<LocationDTO> locationsToLocationDTOs(List<Location> locations) {
        return mapList(locations, locationMapper::toLocationDTO);
    }
}