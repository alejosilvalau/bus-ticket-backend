package com.frro.bus.ticket.features.journey.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frro.bus.ticket.common.utils.OptionalMapperUtil;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.CreateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.UpdateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.SearchLocationDTO;
import com.frro.bus.ticket.features.journey.entities.Location;

@Mapper(componentModel = "spring", uses = OptionalMapperUtil.class)
public interface LocationMapper {
    LocationDTO toLocationDTO(Location location);

    @Mapping(target = "id", ignore = true)
    Location toLocation(CreateLocationDTO createLocationDto);

    @Mapping(target = "cityName", source = "cityName", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "state", source = "state", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "postalCode", source = "postalCode", qualifiedByName = "unwrapOptionalString")
    Location toLocation(UpdateLocationDTO updateLocationDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cityName", source = "cityName", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "state", source = "state", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "postalCode", source = "postalCode", qualifiedByName = "unwrapOptionalString")
    Location toLocation(SearchLocationDTO searchLocationDto);
}
