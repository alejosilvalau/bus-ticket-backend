package com.frro.bus.ticket.driver.mapper;

import com.frro.bus.ticket.driver.dto.DriverRequest;
import com.frro.bus.ticket.driver.dto.DriverResponse;
import com.frro.bus.ticket.driver.model.Driver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverMapper {
    DriverResponse toDriverResponse(Driver driver);
    Driver toDriver(DriverRequest driverRequest);
}
