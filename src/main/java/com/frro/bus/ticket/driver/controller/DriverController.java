package com.frro.bus.ticket.driver.controller;

import com.frro.bus.ticket.driver.dto.DriverRequest;
import com.frro.bus.ticket.driver.dto.DriverResponse;
import com.frro.bus.ticket.driver.service.DriverService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<List<DriverResponse>> findAll() {
        List<DriverResponse> drivers = driverService.findAll();
        return ResponseEntity.ok(drivers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponse> findById(@PathVariable int id) {
        Optional<DriverResponse> driver = driverService.findById(id);
        return driver.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DriverResponse> create(@RequestBody DriverRequest driverRequest) {
        DriverResponse savedDriver = driverService.create(driverRequest);
        return ResponseEntity.ok(savedDriver);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverResponse> updateDriver(@PathVariable int id,
            @RequestBody DriverRequest driverRequest) {
        Optional<DriverResponse> updatedDriver = driverService.update(id, driverRequest);
        return updatedDriver.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DriverResponse> delete(@PathVariable int id) {
        Optional<DriverResponse> deletedDriver = driverService.delete(id);
        return deletedDriver.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
