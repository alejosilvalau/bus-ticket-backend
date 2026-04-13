package com.frro.bus.ticket.driver.controller;

import com.frro.bus.ticket.driver.dto.DriverRequest;
import com.frro.bus.ticket.driver.dto.DriverResponse;
import com.frro.bus.ticket.driver.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @GetMapping
    public List<DriverResponse> getAllDrivers() {
        return driverService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponse> getDriverById(@PathVariable Integer id) {
        return driverService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DriverResponse> createDriver(@RequestBody DriverRequest driverRequest) {
        DriverResponse created = driverService.create(driverRequest);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverResponse> updateDriver(@PathVariable Integer id,
            @RequestBody DriverRequest driverRequest) {
        return driverService.update(id, driverRequest)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Integer id) {
        if (driverService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
