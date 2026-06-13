package com.frro.bus.ticket.features.identity.controllers;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.frro.bus.ticket.common.dto.ApiResponse;
import com.frro.bus.ticket.features.identity.dtos.driver.CreateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.UpdateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.SearchDriverDTO;
import com.frro.bus.ticket.features.identity.services.driver.DriverService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/identity/drivers")
@RequiredArgsConstructor
public class DriverAdminController {

    private static final Logger log = LoggerFactory.getLogger(DriverAdminController.class);

    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<DriverDTO>>> findAll(Pageable pageable) {
        try {
            Page<DriverDTO> drivers = driverService.findAll(pageable);
            return ResponseEntity.ok(ApiResponse.success("Drivers retrieved successfully", drivers));
        } catch (Exception e) {
            log.error("Failed to retrieve drivers", e);
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to retrieve drivers. Please try again later."));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<DriverDTO>>> search(@Valid @RequestBody SearchDriverDTO searchCriteria, Pageable pageable) {
        try {
            Page<DriverDTO> drivers = driverService.search(searchCriteria, pageable);
            return ResponseEntity.ok(ApiResponse.success("Drivers searched successfully", drivers));
        } catch (Exception e) {
            log.error("Failed to search drivers", e);
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to search drivers. Please try again later."));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DriverDTO>> findById(@PathVariable int id) {
        try {
            return driverService.findById(id)
                    .map(driver -> ResponseEntity.ok(ApiResponse.success("Driver retrieved successfully", driver)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Driver not found with id: " + id)));
        } catch (Exception e) {
            log.error("Failed to retrieve driver with id: {}", id, e);
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to retrieve driver. Please try again later."));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DriverDTO>> create(@Valid @RequestBody CreateDriverDTO driverRequest) {
        try {
            DriverDTO savedDriver = driverService.create(driverRequest);
            return ResponseEntity.ok(ApiResponse.success("Driver created successfully", savedDriver));
        } catch (Exception e) {
            log.error("Failed to create driver", e);
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to create driver. Please try again later."));
        }
    }

    @PatchMapping
    public ResponseEntity<ApiResponse<DriverDTO>> update(@Valid @RequestBody UpdateDriverDTO driverRequest) {
        try {
            return driverService.update(driverRequest)
                    .map(driver -> ResponseEntity.ok(ApiResponse.success("Driver updated successfully", driver)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Driver not found with id: " + driverRequest.id())));
        } catch (Exception e) {
            log.error("Failed to update driver with id: {}", driverRequest.id(), e);
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to update driver. Please try again later."));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<DriverDTO>> delete(@PathVariable int id) {
        try {
            return driverService.delete(id)
                    .map(driver -> ResponseEntity.ok(ApiResponse.success("Driver deleted successfully", driver)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Driver not found with id: " + id)));
        } catch (Exception e) {
            log.error("Failed to delete driver with id: {}", id, e);
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to delete driver. Please try again later."));
        }
    }
}
