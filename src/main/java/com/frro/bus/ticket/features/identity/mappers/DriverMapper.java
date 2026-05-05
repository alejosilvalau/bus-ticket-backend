package com.frro.bus.ticket.features.identity.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.frro.bus.ticket.common.utils.entities.trip.TripMapperDTOSingleUtil;
import com.frro.bus.ticket.common.utils.entities.trip.TripMapperDTOListUtil;
import com.frro.bus.ticket.common.utils.EntityMapperUtil;
import com.frro.bus.ticket.common.utils.OptionalMapperUtil;
import com.frro.bus.ticket.features.identity.dtos.driver.CreateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverFullDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.UpdateDriverDTO;
import com.frro.bus.ticket.features.identity.entities.Driver;

@Mapper(componentModel = "spring", uses = { OptionalMapperUtil.class, EntityMapperUtil.class,
        TripMapperDTOSingleUtil.class, TripMapperDTOListUtil.class })
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
}
