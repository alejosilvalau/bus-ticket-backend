package com.frro.bus.ticket.features.journey.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frro.bus.ticket.common.utils.entities.EntityMapperUtil;
import com.frro.bus.ticket.common.utils.DataTypeMapperUtil;
import com.frro.bus.ticket.common.utils.entities.trip.TripMapperDTOSingleUtil;
import com.frro.bus.ticket.common.utils.entities.trip.TripMapperDTOListUtil;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.LocationFullDTO;
import com.frro.bus.ticket.features.journey.dtos.location.CreateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.UpdateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.SearchLocationDTO;
import com.frro.bus.ticket.features.journey.entities.Location;

@Mapper(componentModel = "spring", uses = { DataTypeMapperUtil.class, EntityMapperUtil.class,
        TripMapperDTOSingleUtil.class, TripMapperDTOListUtil.class })
public interface LocationMapper {
    LocationDTO toLocationDTO(Location location);

    @Mapping(target = "tripsOrigin", source = "tripsOrigin", qualifiedByName = "tripsToTripDTOs")
    @Mapping(target = "tripsDestination", source = "tripsDestination", qualifiedByName = "tripsToTripDTOs")
    LocationFullDTO toLocationFullDTO(Location location);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tripsOrigin", ignore = true)
    @Mapping(target = "tripsDestination", ignore = true)
    Location toLocation(CreateLocationDTO createLocationDto);

    @Mapping(target = "cityName", source = "cityName", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "state", source = "state", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "postalCode", source = "postalCode", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "tripsOrigin", ignore = true)
    @Mapping(target = "tripsDestination", ignore = true)
    Location toLocation(UpdateLocationDTO updateLocationDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cityName", source = "cityName", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "state", source = "state", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "postalCode", source = "postalCode", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "tripsOrigin", ignore = true)
    @Mapping(target = "tripsDestination", ignore = true)
    Location toLocation(SearchLocationDTO searchLocationDto);
}
