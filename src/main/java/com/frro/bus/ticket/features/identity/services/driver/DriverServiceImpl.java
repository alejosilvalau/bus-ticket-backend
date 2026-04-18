package com.frro.bus.ticket.features.identity.services.driver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.frro.bus.ticket.features.identity.dtos.driver.CreateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.UpdateDriverDTO;
import com.frro.bus.ticket.features.identity.entities.Driver;
import com.frro.bus.ticket.features.identity.mappers.DriverMapper;
import com.frro.bus.ticket.features.identity.repositories.DriverRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Override
    public List<DriverDTO> findAll() {
        return driverRepository.findAll().stream().map(driverMapper::toDriverDTO).toList();
    }

    @Override
    public Optional<DriverDTO> findById(int id) {
        return driverRepository.findById(id).map(driverMapper::toDriverDTO);
    }

    @Override
    public DriverDTO create(CreateDriverDTO driverRequest) {
        Driver driver = driverMapper.toDriver(driverRequest);

        // Search for existing one for active false

        Driver saved = driverRepository.save(driver);
        return driverMapper.toDriverDTO(saved);
    }

    @Override
    public Optional<DriverDTO> update(int id, UpdateDriverDTO driverRequest) {
        return driverRepository.findById(id).map(existingDriver -> {
            existingDriver.setFirstName(driverRequest.firstName());
            existingDriver.setLastName(driverRequest.lastName());
            existingDriver.setIsActive(driverRequest.isActive());
            existingDriver.setLicenseNumber(driverRequest.licenseNumber());
            existingDriver.setPhoneNumber(driverRequest.phoneNumber());

            Driver savedDriver = driverRepository.save(existingDriver);
            return driverMapper.toDriverDTO(savedDriver);
        });
    }

    @Override
    public Optional<DriverDTO> logicalDelete(int id) {
        return driverRepository.findById(id).map(existingDriver -> {
            existingDriver.setIsActive(false);
            Driver savedDriver = driverRepository.save(existingDriver);
            return driverMapper.toDriverDTO(savedDriver);
        });
    }

    @Override
    public Optional<DriverDTO> delete(int id) {
        return driverRepository.findById(id).map(existingDriver -> {
            driverRepository.delete(existingDriver);
            return driverMapper.toDriverDTO(existingDriver);
        });
    }
}
