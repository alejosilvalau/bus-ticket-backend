package com.frro.bus.ticket.features.identity.services;

import java.util.List;
import java.util.Optional;

import com.frro.bus.ticket.features.identity.dtos.driver.CreateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.UpdateDriverDTO;

public interface DriverManagementService {
    List<DriverDTO> findAll();

    Optional<DriverDTO> findById(int id);

    DriverDTO create(CreateDriverDTO userRequest);

    Optional<DriverDTO> delete(int id);

    Optional<DriverDTO> update(int id, UpdateDriverDTO userRequest);
}
