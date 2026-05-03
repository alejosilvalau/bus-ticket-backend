package com.frro.bus.ticket.features.fleet.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.CreateBusDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.UpdateBusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.CreateSeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.UpdateSeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.CreateSeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.UpdateSeatTypeDTO;
import com.frro.bus.ticket.features.fleet.services.architect.ArchitectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/fleet/architect")
@RequiredArgsConstructor
public class ArchitectController {
    private final ArchitectService architectService;

    @PostMapping("/buses")
    public ResponseEntity<BusDTO> createBus(@RequestBody CreateBusDTO busRequest) {
        BusDTO savedBus = architectService.createBus(busRequest);
        return ResponseEntity.ok(savedBus);
    }

    @PostMapping("/seats")
    public ResponseEntity<SeatDTO> createSeat(@RequestBody CreateSeatDTO seatRequest) {
        SeatDTO savedSeat = architectService.createSeat(seatRequest);
        return ResponseEntity.ok(savedSeat);
    }

    @PostMapping("/seat-types")
    public ResponseEntity<SeatTypeDTO> createSeatType(@RequestBody CreateSeatTypeDTO seatTypeRequest) {
        SeatTypeDTO savedSeatType = architectService.createSeatType(seatTypeRequest);
        return ResponseEntity.ok(savedSeatType);
    }

    @PatchMapping("/buses")
    public ResponseEntity<BusDTO> updateBus(@RequestBody UpdateBusDTO busRequest) {
        Optional<BusDTO> updatedBus = architectService.updateBus(busRequest);
        return updatedBus.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/seats")
    public ResponseEntity<SeatDTO> updateSeat(@RequestBody UpdateSeatDTO seatRequest) {
        Optional<SeatDTO> updatedSeat = architectService.updateSeat(seatRequest);
        return updatedSeat.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/seat-types")
    public ResponseEntity<SeatTypeDTO> updateSeatType(@RequestBody UpdateSeatTypeDTO seatTypeRequest) {
        Optional<SeatTypeDTO> updatedSeatType = architectService.updateSeatType(seatTypeRequest);
        return updatedSeatType.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/buses/{id}")
    public ResponseEntity<BusDTO> deleteBus(@PathVariable int id) {
        Optional<BusDTO> deletedBus = architectService.deleteBus(id);
        return deletedBus.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/seats/{id}")
    public ResponseEntity<SeatDTO> deleteSeat(@PathVariable int id) {
        Optional<SeatDTO> deletedSeat = architectService.deleteSeat(id);
        return deletedSeat.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/seat-types/{id}")
    public ResponseEntity<SeatTypeDTO> deleteSeatType(@PathVariable int id) {
        Optional<SeatTypeDTO> deletedSeatType = architectService.deleteSeatType(id);
        return deletedSeatType.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
