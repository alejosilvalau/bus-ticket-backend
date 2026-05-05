package com.frro.bus.ticket.common.utils.entities.driver;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.frro.bus.ticket.common.utils.entities.EntityMapperDTOUtil;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.entities.Driver;
import com.frro.bus.ticket.features.identity.mappers.DriverMapper;

@Mapper(componentModel = "spring")
public abstract class DriverMapperDTOListUtil extends EntityMapperDTOUtil {

    @Autowired
    protected DriverMapper driverMapper;

    @Named("driversToDriverDTOs")
    public List<DriverDTO> driversToDriverDTOs(List<Driver> drivers) {
        return mapList(drivers, driverMapper::toDriverDTO);
    }
}