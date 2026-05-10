package com.frro.bus.ticket.features.identity.services.driver;

import java.util.List;
import java.util.Optional;

import com.frro.bus.ticket.features.identity.dtos.driver.CreateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.UpdateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.SearchDriverDTO;

public interface DriverService {
    List<DriverDTO> findAll();

    Optional<DriverDTO> findById(int id);

    List<DriverDTO> search(SearchDriverDTO searchCriteria);

    DriverDTO create(CreateDriverDTO userRequest);

    Optional<DriverDTO> update(UpdateDriverDTO userRequest);

    Optional<DriverDTO> logicalDelete(int id);

    Optional<DriverDTO> delete(int id);
}
