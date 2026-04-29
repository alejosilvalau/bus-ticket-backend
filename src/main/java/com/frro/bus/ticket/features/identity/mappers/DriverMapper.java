package com.frro.bus.ticket.features.identity.mappers;

import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;

import com.frro.bus.ticket.features.identity.dtos.driver.CreateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.UpdateDriverDTO;
import com.frro.bus.ticket.features.identity.entities.Driver;

@Mapper(componentModel = "spring")
public interface DriverMapper {
    DriverDTO toDriverDTO(Driver driver);

    // @Mapping(target = "trips", ignore = true)
    Driver toDriver(CreateDriverDTO createDriverDto);

    // @Mapping(target = "trips", ignore = true)
    Driver toDriver(UpdateDriverDTO updateDriverDto);
}
