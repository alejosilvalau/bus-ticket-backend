package com.frro.bus.ticket.features.identity.services.driver;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.frro.bus.ticket.features.identity.dtos.driver.CreateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.UpdateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.SearchDriverDTO;

public interface DriverService {
    Page<DriverDTO> findAll(Pageable pageable);

    Page<DriverDTO> search(SearchDriverDTO searchCriteria, Pageable pageable);

    Optional<DriverDTO> findById(int id);

    DriverDTO create(CreateDriverDTO userRequest);

    Optional<DriverDTO> update(UpdateDriverDTO userRequest);

    Optional<DriverDTO> logicalDelete(int id);

    Optional<DriverDTO> delete(int id);
}
