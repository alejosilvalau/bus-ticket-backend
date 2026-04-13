package com.frro.bus.ticket.bus.controller;

import com.frro.bus.ticket.bus.dto.BusRequest;
import com.frro.bus.ticket.bus.dto.BusResponse;
import com.frro.bus.ticket.bus.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/buses")
@RequiredArgsConstructor
public class BusController {
    private final BusService service;

    @GetMapping
    public List<BusResponse> getAllBuses() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusResponse> getBusById(@PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BusResponse> createBus(@RequestBody BusRequest request) {
        BusResponse created = service.create(request);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusResponse> updateBus(@PathVariable Integer id, @RequestBody BusRequest request) {
        return service.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Integer id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
