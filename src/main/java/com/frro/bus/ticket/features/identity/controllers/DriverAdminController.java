package com.frro.bus.ticket.features.identity.controllers;

import lombok.RequiredArgsConstructor;

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

@RestController
@RequestMapping("/api/v1/identity/drivers")
@RequiredArgsConstructor
public class DriverAdminController {
    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<DriverDTO>>> findAll(Pageable pageable) {
        try {
            Page<DriverDTO> drivers = driverService.findAll(pageable);
            return ResponseEntity.ok(ApiResponse.success("Drivers retrieved successfully", drivers));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to retrieve drivers: " + e.getMessage()));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<DriverDTO>>> search(@RequestBody SearchDriverDTO searchCriteria, Pageable pageable) {
        try {
            Page<DriverDTO> drivers = driverService.search(searchCriteria, pageable);
            return ResponseEntity.ok(ApiResponse.success("Drivers searched successfully", drivers));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to search drivers: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DriverDTO>> findById(@PathVariable int id) {
        try {
            return driverService.findById(id)
                    .map(driver -> ResponseEntity.ok(ApiResponse.success("Driver retrieved successfully", driver)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Driver not found with id: " + id)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to retrieve driver: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DriverDTO>> create(@RequestBody CreateDriverDTO driverRequest) {
        try {
            DriverDTO savedDriver = driverService.create(driverRequest);
            return ResponseEntity.ok(ApiResponse.success("Driver created successfully", savedDriver));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to create driver: " + e.getMessage()));
        }
    }

    @PatchMapping
    public ResponseEntity<ApiResponse<DriverDTO>> update(@RequestBody UpdateDriverDTO driverRequest) {
        try {
            return driverService.update(driverRequest)
                    .map(driver -> ResponseEntity.ok(ApiResponse.success("Driver updated successfully", driver)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Driver not found with id: " + driverRequest.id())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to update driver: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<DriverDTO>> delete(@PathVariable int id) {
        try {
            return driverService.delete(id)
                    .map(driver -> ResponseEntity.ok(ApiResponse.success("Driver deleted successfully", driver)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Driver not found with id: " + id)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to delete driver: " + e.getMessage()));
        }
    }
}
