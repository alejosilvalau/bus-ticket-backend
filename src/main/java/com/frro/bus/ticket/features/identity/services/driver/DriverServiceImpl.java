package com.frro.bus.ticket.features.identity.services.driver;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.frro.bus.ticket.features.identity.dtos.driver.CreateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.UpdateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.SearchDriverDTO;
import com.frro.bus.ticket.features.identity.entities.Driver;
import com.frro.bus.ticket.features.identity.mappers.DriverMapper;
import com.frro.bus.ticket.features.identity.repositories.DriverRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Override
    public Page<DriverDTO> findAll(Pageable pageable) {
        return driverRepository.findAll(pageable).map(driverMapper::toDriverDTO);
    }

    @Override
    public Page<DriverDTO> search(SearchDriverDTO searchCriteria, Pageable pageable) {
        return driverRepository.searchDrivers(
                searchCriteria.firstName().orElse(null),
                searchCriteria.lastName().orElse(null),
                searchCriteria.isActive().orElse(null),
                searchCriteria.licenseNumber().orElse(null),
                searchCriteria.phoneNumber().orElse(null),
                pageable)
                .map(driverMapper::toDriverDTO);
    }

    @Override
    public Optional<DriverDTO> findById(int id) {
        return driverRepository.findById(id).map(driverMapper::toDriverDTO);
    }

    @Override
    public DriverDTO create(CreateDriverDTO driverRequest) {
        Driver driver = driverMapper.toDriver(driverRequest);
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
