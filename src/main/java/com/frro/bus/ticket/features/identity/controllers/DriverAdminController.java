package com.frro.bus.ticket.features.identity.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.frro.bus.ticket.features.identity.dtos.driver.CreateDriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.DriverDTO;
import com.frro.bus.ticket.features.identity.dtos.driver.UpdateDriverDTO;
import com.frro.bus.ticket.features.identity.services.driver.DriverService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/identity/drivers")
@RequiredArgsConstructor
public class DriverAdminController {
    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<List<DriverDTO>> findAll() {
        List<DriverDTO> drivers = driverService.findAll();
        return ResponseEntity.ok(drivers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDTO> findById(@PathVariable int id) {
        Optional<DriverDTO> driver = driverService.findById(id);
        return driver.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DriverDTO> create(@RequestBody CreateDriverDTO driverRequest) {
        DriverDTO savedDriver = driverService.create(driverRequest);
        return ResponseEntity.ok(savedDriver);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverDTO> updateDriver(@PathVariable int id,
            @RequestBody UpdateDriverDTO driverRequest) {
        Optional<DriverDTO> updatedDriver = driverService.update(id, driverRequest);
        return updatedDriver.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DriverDTO> delete(@PathVariable int id) {
        Optional<DriverDTO> deletedDriver = driverService.delete(id);
        return deletedDriver.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
