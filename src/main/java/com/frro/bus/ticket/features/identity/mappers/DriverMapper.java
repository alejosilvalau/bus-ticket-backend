package com.frro.bus.ticket.features.identity.mappers;

import java.util.List;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.frro.bus.ticket.common.utils.EntityMapperDTOUtil;
import com.frro.bus.ticket.common.utils.EntityMapperUtil;
import com.frro.bus.ticket.common.utils.OptionalMapperUtil;
import com.frro.bus.ticket.features.identity.dtos.driver.CreateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverFullDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.UpdateDriverDTO;
import com.frro.bus.ticket.features.identity.entities.Driver;
import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;
import com.frro.bus.ticket.features.journey.entities.Trip;
import com.frro.bus.ticket.features.journey.mappers.TripMapper;

@Mapper(componentModel = "spring", uses = { OptionalMapperUtil.class, EntityMapperUtil.class,
        EntityMapperDTOUtil.class })
public interface DriverMapper {
    @Mapping(target = "isActive", source = "active")
    DriverDTO toDriverDTO(Driver driver);

    @Mapping(target = "isActive", source = "active")
    @Mapping(target = "trips", source = "trips", qualifiedByName = "tripsToTripDTOs")
    DriverFullDTO toDriverFullDTO(Driver driver);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", source = "isActive")
    @Mapping(target = "trips", ignore = true)
    Driver toDriver(CreateDriverDTO createDriverDto);

    @Mapping(target = "firstName", source = "firstName", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "lastName", source = "lastName", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "active", source = "isActive", qualifiedByName = "unwrapOptionalBoolean")
    @Mapping(target = "licenseNumber", source = "licenseNumber", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "phoneNumber", source = "phoneNumber", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "trips", ignore = true)
    Driver toDriver(UpdateDriverDTO updateDriverDto);

    @Named("tripsToTripDTOs")
    default List<TripDTO> tripsToTripDTOs(List<Trip> trips, @Context TripMapper tripMapper) {
        return trips != null ? trips.stream().map(tripMapper::toTripDTO).toList() : List.of();
    }
}
