package com.frro.bus.ticket.features.identity.mappers;

import org.mapstruct.Mapper;

import com.frro.bus.ticket.features.identity.dtos.driver.CreateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.UpdateDriverDTO;
import com.frro.bus.ticket.features.identity.entities.Driver;

@Mapper(componentModel = "spring")
public interface DriverMapper {
    DriverDTO toDriverDTO(Driver driver);

    Driver toDriver(CreateDriverDTO driverDto);

    Driver toDriver(UpdateDriverDTO driverDto);
}
