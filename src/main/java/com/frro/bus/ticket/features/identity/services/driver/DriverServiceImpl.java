package com.frro.bus.ticket.features.identity.services.driver;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.frro.bus.ticket.common.dto.PageResponse;
import com.frro.bus.ticket.common.exceptions.DuplicateResourceException;
import com.frro.bus.ticket.common.exceptions.ResourceNotFoundException;
import com.frro.bus.ticket.features.identity.dtos.driver.CreateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.UpdateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.SearchDriverDTO;
import com.frro.bus.ticket.features.identity.entities.Driver;
import com.frro.bus.ticket.features.identity.mappers.DriverMapper;
import com.frro.bus.ticket.features.identity.repositories.DriverRepository;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Override
    public PageResponse<DriverDTO> findAll(Pageable pageable) {
        Page<DriverDTO> page = driverRepository.findAll(pageable).map(driverMapper::toDriverDTO);
        return toPageResponse(page);
    }

    @Override
    public PageResponse<DriverDTO> search(SearchDriverDTO searchCriteria, Pageable pageable) {
        Page<DriverDTO> page = driverRepository.searchDrivers(
                searchCriteria.firstName().orElse(null),
                searchCriteria.lastName().orElse(null),
                searchCriteria.isActive().orElse(null),
                searchCriteria.licenseNumber().orElse(null),
                searchCriteria.phoneNumber().orElse(null),
                pageable)
                .map(driverMapper::toDriverDTO);
        return toPageResponse(page);
    }

    @Override
    public DriverDTO findById(int id) {
        return driverRepository.findById(id)
                .map(driverMapper::toDriverDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Driver", "id", id));
    }

    @Override
    public DriverDTO create(CreateDriverDTO driverRequest) {
        driverRepository.findByLicenseNumber(driverRequest.licenseNumber())
                .ifPresent(driver -> {
                    throw new DuplicateResourceException("Driver", "licenseNumber", driverRequest.licenseNumber());
                });

        Driver driver = driverMapper.toDriver(driverRequest);
        Driver saved = driverRepository.save(driver);
        return driverMapper.toDriverDTO(saved);
    }

    @Override
    public DriverDTO update(UpdateDriverDTO driverRequest) {
        Driver existingDriver = driverRepository.findById(driverRequest.id())
                .orElseThrow(() -> new ResourceNotFoundException("Driver", "id", driverRequest.id()));

        driverRequest.licenseNumber().ifPresent(newLicense -> {
            driverRepository.findByLicenseNumber(newLicense)
                    .filter(found -> found.getId() != driverRequest.id())
                    .ifPresent(driver -> {
                        throw new DuplicateResourceException("Driver", "licenseNumber", newLicense);
                    });
        });

        driverRequest.firstName().ifPresent(existingDriver::setFirstName);
        driverRequest.lastName().ifPresent(existingDriver::setLastName);
        driverRequest.isActive().ifPresent(existingDriver::setActive);
        driverRequest.licenseNumber().ifPresent(existingDriver::setLicenseNumber);
        driverRequest.phoneNumber().ifPresent(existingDriver::setPhoneNumber);

        Driver savedDriver = driverRepository.save(existingDriver);
        return driverMapper.toDriverDTO(savedDriver);
    }

    @Override
    public DriverDTO logicalDelete(int id) {
        Driver existingDriver = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver", "id", id));
        existingDriver.setActive(false);
        Driver savedDriver = driverRepository.save(existingDriver);
        return driverMapper.toDriverDTO(savedDriver);
    }

    @Override
    public DriverDTO delete(int id) {
        Driver existingDriver = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver", "id", id));
        driverRepository.delete(existingDriver);
        return driverMapper.toDriverDTO(existingDriver);
    }

    private <T> PageResponse<T> toPageResponse(Page<T> page) {
        return PageResponse.of(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.isEmpty());
    }
}
