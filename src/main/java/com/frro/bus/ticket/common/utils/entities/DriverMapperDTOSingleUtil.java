package com.frro.bus.ticket.common.utils.entities;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.frro.bus.ticket.common.utils.EntityMapperDTOUtil;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.entities.Driver;
import com.frro.bus.ticket.features.identity.mappers.DriverMapper;

@Mapper(componentModel = "spring")
public abstract class DriverMapperDTOSingleUtil extends EntityMapperDTOUtil {

    @Autowired
    protected DriverMapper driverMapper;

    @Named("driverToDriverDTO")
    public DriverDTO driverToDriverDTO(Driver driver) {
        return mapSingle(driver, driverMapper::toDriverDTO);
    }
}
