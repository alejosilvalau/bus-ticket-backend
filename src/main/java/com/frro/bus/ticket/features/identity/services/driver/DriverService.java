package com.frro.bus.ticket.features.identity.services.driver;

import java.util.List;
import java.util.Optional;

import com.frro.bus.ticket.features.identity.dtos.driver.CreateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.UpdateDriverDTO;

public interface DriverService {
    List<DriverDTO> findAll();

    Optional<DriverDTO> findById(int id);

    DriverDTO create(CreateDriverDTO userRequest);

    Optional<DriverDTO> update(UpdateDriverDTO userRequest);

    Optional<DriverDTO> delete(int id);

    Optional<DriverDTO> logicalDelete(int id);
}
