package com.frro.bus.ticket.driver.service;

import com.frro.bus.ticket.driver.dto.DriverRequest;
import com.frro.bus.ticket.driver.dto.DriverResponse;
import com.frro.bus.ticket.driver.mapper.DriverMapper;
import com.frro.bus.ticket.driver.model.Driver;
import com.frro.bus.ticket.driver.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    public List<DriverResponse> findAll() {
        return driverRepository.findAll().stream().map(driverMapper::toDriverResponse).toList();
    }

    public Optional<DriverResponse> findById(int id) {
        return driverRepository.findById(id).map(driverMapper::toDriverResponse);
    }

    public DriverResponse create(DriverRequest driverRequest) {
        Driver driver = driverMapper.toDriver(driverRequest);
        Driver saved = driverRepository.save(driver);
        return driverMapper.toDriverResponse(saved);
    }

    public Optional<DriverResponse> update(int id, DriverRequest driverRequest) {
        return driverRepository.findById(id).map(existing -> {
            Driver toUpdate = driverMapper.toDriver(driverRequest);
            toUpdate.setId(id);
            Driver saved = driverRepository.save(toUpdate);
            return driverMapper.toDriverResponse(saved);
        });
    }

    public Optional<DriverResponse> delete(int id) {
        return driverRepository.findById(id).map(existing -> {
            driverRepository.delete(existing);
            return driverMapper.toDriverResponse(existing);
        });
    }
}
