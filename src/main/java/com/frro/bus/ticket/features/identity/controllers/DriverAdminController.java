package com.frro.bus.ticket.features.identity.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.frro.bus.ticket.common.dto.ApiResponse;
import com.frro.bus.ticket.common.security.endpointhelpers.AdminEndpoint;
import com.frro.bus.ticket.features.identity.dtos.driver.CreateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.UpdateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.SearchDriverDTO;
import com.frro.bus.ticket.features.identity.services.driver.DriverService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/identity/drivers")
@AdminEndpoint
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class DriverAdminController {

    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<DriverDTO>>> findAll(Pageable pageable) {
        Page<DriverDTO> drivers = driverService.findAll(pageable);
        return ResponseEntity.ok(ApiResponse.success("Drivers retrieved successfully", drivers));
    }

    @PostMapping("/search")
    public ResponseEntity<ApiResponse<Page<DriverDTO>>> search(@Valid @RequestBody SearchDriverDTO searchCriteria,
            Pageable pageable) {
        Page<DriverDTO> drivers = driverService.search(searchCriteria, pageable);
        return ResponseEntity.ok(ApiResponse.success("Drivers searched successfully", drivers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DriverDTO>> findById(@PathVariable int id) {
        DriverDTO driver = driverService.findById(id);
        return ResponseEntity.ok(ApiResponse.success("Driver retrieved successfully", driver));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DriverDTO>> create(@Valid @RequestBody CreateDriverDTO driverRequest) {
        DriverDTO savedDriver = driverService.create(driverRequest);
        return ResponseEntity.ok(ApiResponse.success("Driver created successfully", savedDriver));
    }

    @PatchMapping
    public ResponseEntity<ApiResponse<DriverDTO>> update(@Valid @RequestBody UpdateDriverDTO driverRequest) {
        DriverDTO driver = driverService.update(driverRequest);
        return ResponseEntity.ok(ApiResponse.success("Driver updated successfully", driver));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<DriverDTO>> delete(@PathVariable int id) {
        DriverDTO driver = driverService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Driver deleted successfully", driver));
    }
}
