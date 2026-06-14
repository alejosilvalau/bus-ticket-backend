package com.frro.bus.ticket.features.identity.services.driver;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.frro.bus.ticket.features.identity.dtos.driver.CreateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.UpdateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.SearchDriverDTO;

public interface DriverService {
    Page<DriverDTO> findAll(Pageable pageable);

    Page<DriverDTO> search(SearchDriverDTO searchCriteria, Pageable pageable);

    DriverDTO findById(int id);

    DriverDTO create(CreateDriverDTO userRequest);

    DriverDTO update(UpdateDriverDTO userRequest);

    DriverDTO logicalDelete(int id);

    DriverDTO delete(int id);
}
