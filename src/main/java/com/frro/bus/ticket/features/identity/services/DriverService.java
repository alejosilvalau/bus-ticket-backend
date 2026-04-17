package com.frro.bus.ticket.driver.service;

import com.frro.bus.ticket.driver.dto.DriverRequest;
import com.frro.bus.ticket.driver.dto.DriverResponse;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    List<DriverResponse> findAll();

    Optional<DriverResponse> findById(int id);

    DriverResponse create(DriverRequest userRequest);

    Optional<DriverResponse> delete(int id);

    Optional<DriverResponse> update(int id, DriverRequest userRequest);
}
