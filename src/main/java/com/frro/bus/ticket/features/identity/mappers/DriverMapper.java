package com.frro.bus.ticket.features.identity.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.frro.bus.ticket.common.utils.OptionalMapperUtil;
import com.frro.bus.ticket.features.identity.dtos.driver.CreateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.UpdateDriverDTO;
import com.frro.bus.ticket.features.identity.entities.Driver;
import com.frro.bus.ticket.features.journey.entities.Trip;

import java.util.List;

@Mapper(componentModel = "spring", uses = OptionalMapperUtil.class)
public interface DriverMapper {
    @Mapping(target = "isActive", source = "active")
    @Mapping(target = "idTrips", source = "trips", qualifiedByName = "tripsToIds")
    DriverDTO toDriverDTO(Driver driver);

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

    @Named("tripsToIds")
    default List<Integer> tripsToIds(List<Trip> trips) {
        return trips != null ? trips.stream().map(Trip::getId).toList() : List.of();
    }
}
