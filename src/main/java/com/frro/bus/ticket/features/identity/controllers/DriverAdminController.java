package com.frro.bus.ticket.features.identity.controllers;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.frro.bus.ticket.common.dto.ApiResponse;
import com.frro.bus.ticket.common.dto.PaginationMeta;
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
    public ResponseEntity<ApiResponse<List<DriverDTO>>> findAll(Pageable pageable) {
        Page<DriverDTO> drivers = driverService.findAll(pageable);
        return ResponseEntity.ok(ApiResponse.success(drivers.getContent(), PaginationMeta.fromPage(drivers)));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<DriverDTO>>> search(@RequestBody SearchDriverDTO searchCriteria, Pageable pageable) {
        Page<DriverDTO> drivers = driverService.search(searchCriteria, pageable);
        return ResponseEntity.ok(ApiResponse.success(drivers.getContent(), PaginationMeta.fromPage(drivers)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DriverDTO>> findById(@PathVariable int id) {
        return driverService.findById(id)
                .map(driver -> ResponseEntity.ok(ApiResponse.success(driver)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Driver not found")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DriverDTO>> create(@RequestBody CreateDriverDTO driverRequest) {
        DriverDTO savedDriver = driverService.create(driverRequest);
        return ResponseEntity.ok(ApiResponse.success(savedDriver));
    }

    @PatchMapping
    public ResponseEntity<ApiResponse<DriverDTO>> update(@RequestBody UpdateDriverDTO driverRequest) {
        return driverService.update(driverRequest)
                .map(driver -> ResponseEntity.ok(ApiResponse.success(driver)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Driver not found")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<DriverDTO>> delete(@PathVariable int id) {
        return driverService.delete(id)
                .map(driver -> ResponseEntity.ok(ApiResponse.success(driver)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Driver not found")));
    }
}
