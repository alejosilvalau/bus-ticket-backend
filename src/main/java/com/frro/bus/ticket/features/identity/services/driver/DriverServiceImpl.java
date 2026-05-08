package com.frro.bus.ticket.features.identity.services.driver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.frro.bus.ticket.features.identity.dtos.driver.CreateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.UpdateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.SearchDriverDTO;
import com.frro.bus.ticket.features.identity.entities.Driver;
import com.frro.bus.ticket.features.identity.mappers.DriverMapper;
import com.frro.bus.ticket.features.identity.repositories.DriverRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Override
    public List<DriverDTO> findAll() {
        return driverRepository.findAll().stream().map(driver -> {
            return driverMapper.toDriverDTO(driver);
        }).toList();
    }

    @Override
    public Optional<DriverDTO> findById(int id) {
        return driverRepository.findById(id).map(driver -> {
            return driverMapper.toDriverDTO(driver);
        });
    }

    @Override
    public List<DriverDTO> search(SearchDriverDTO searchCriteria) {
        ArrayList<DriverDTO> list = new ArrayList<>();
        return list;
    }

    @Override
    public DriverDTO create(CreateDriverDTO driverRequest) {
        Driver driver = driverMapper.toDriver(driverRequest);

        // Search for existing one for active false

        Driver saved = driverRepository.save(driver);
        return driverMapper.toDriverDTO(saved);
    }

    @Override
    public Optional<DriverDTO> update(UpdateDriverDTO driverRequest) {
        return driverRepository.findById(driverRequest.id()).map(existingDriver -> {
            driverRequest.firstName().ifPresent(existingDriver::setFirstName);
            driverRequest.lastName().ifPresent(existingDriver::setLastName);
            driverRequest.isActive().ifPresent(existingDriver::setActive);
            driverRequest.licenseNumber().ifPresent(existingDriver::setLicenseNumber);
            driverRequest.phoneNumber().ifPresent(existingDriver::setPhoneNumber);

            Driver savedDriver = driverRepository.save(existingDriver);
            return driverMapper.toDriverDTO(savedDriver);
        });
    }

    @Override
    public Optional<DriverDTO> logicalDelete(int id) {
        return driverRepository.findById(id).map(existingDriver -> {
            existingDriver.setActive(false);
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
