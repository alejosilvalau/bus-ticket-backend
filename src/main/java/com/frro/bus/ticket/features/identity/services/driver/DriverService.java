package com.frro.bus.ticket.features.identity.services.driver;

import org.springframework.data.domain.Pageable;

import com.frro.bus.ticket.common.dto.PageResponse;
import com.frro.bus.ticket.features.identity.dtos.driver.CreateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.UpdateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.SearchDriverDTO;

public interface DriverService {
    PageResponse<DriverDTO> findAll(Pageable pageable);

    PageResponse<DriverDTO> search(SearchDriverDTO searchCriteria, Pageable pageable);

    DriverDTO findById(int id);

    DriverDTO create(CreateDriverDTO userRequest);

    DriverDTO update(UpdateDriverDTO userRequest);

    DriverDTO logicalDelete(int id);

    DriverDTO delete(int id);
}
