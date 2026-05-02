package com.frro.bus.ticket.features.identity.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frro.bus.ticket.common.utils.OptionalMapperUtil;
import com.frro.bus.ticket.features.identity.dtos.driver.CreateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.UpdateDriverDTO;
import com.frro.bus.ticket.features.identity.entities.Driver;

@Mapper(componentModel = "spring", uses = OptionalMapperUtil.class)
public interface DriverMapper {
    @Mapping(target = "isActive", source = "active")
    DriverDTO toDriverDTO(Driver driver);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", source = "isActive")
    Driver toDriver(CreateDriverDTO createDriverDto);

    @Mapping(target = "firstName", source = "firstName", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "lastName", source = "lastName", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "active", source = "isActive", qualifiedByName = "unwrapOptionalBoolean")
    @Mapping(target = "licenseNumber", source = "licenseNumber", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "phoneNumber", source = "phoneNumber", qualifiedByName = "unwrapOptionalString")
    Driver toDriver(UpdateDriverDTO updateDriverDto);
}
