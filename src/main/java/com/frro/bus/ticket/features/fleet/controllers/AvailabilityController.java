package com.frro.bus.ticket.features.fleet.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;
import com.frro.bus.ticket.features.fleet.services.availability.AvailabilityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/fleet/availability")
@RequiredArgsConstructor
public class AvailabilityController {
    private final AvailabilityService availabilityService;

    @GetMapping("/buses")
    public ResponseEntity<List<BusDTO>> findAllBuses() {
        List<BusDTO> buses = availabilityService.findAllBuses();
        return ResponseEntity.ok(buses);
    }

    @GetMapping("/seats")
    public ResponseEntity<List<SeatDTO>> findAllSeats() {
        List<SeatDTO> seats = availabilityService.findAllSeats();
        return ResponseEntity.ok(seats);
    }

    @GetMapping("/seat-types")
    public ResponseEntity<List<SeatTypeDTO>> findAllSeatTypes() {
        List<SeatTypeDTO> seatTypes = availabilityService.findAllSeatTypes();
        return ResponseEntity.ok(seatTypes);
    }

    @GetMapping("/buses/{id}")
    public ResponseEntity<BusDTO> findBusById(@PathVariable int id) {
        Optional<BusDTO> bus = availabilityService.findBusById(id);
        return bus.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/seats/{id}")
    public ResponseEntity<SeatDTO> findSeatById(@PathVariable int id) {
        Optional<SeatDTO> seat = availabilityService.findSeatById(id);
        return seat.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/seat-types/{id}")
    public ResponseEntity<SeatTypeDTO> findSeatTypeById(@PathVariable int id) {
        Optional<SeatTypeDTO> seatType = availabilityService.findSeatTypeById(id);
        return seatType.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
