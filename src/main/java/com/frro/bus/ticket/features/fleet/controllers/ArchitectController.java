package com.frro.bus.ticket.features.fleet.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.common.dto.ApiResponse;
import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.CreateBusDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.UpdateBusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatFullDTO;
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
    public ResponseEntity<ApiResponse<BusDTO>> createBus(@RequestBody CreateBusDTO busRequest) {
        BusDTO savedBus = architectService.createBus(busRequest);
        return ResponseEntity.ok(ApiResponse.success(savedBus));
    }

    @PatchMapping("/buses")
    public ResponseEntity<ApiResponse<BusDTO>> updateBus(@RequestBody UpdateBusDTO busRequest) {
        return architectService.updateBus(busRequest)
                .map(bus -> ResponseEntity.ok(ApiResponse.success(bus)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Bus not found")));
    }

    @DeleteMapping("/buses/{id}")
    public ResponseEntity<ApiResponse<BusDTO>> deleteBus(@PathVariable int id) {
        return architectService.deleteBus(id)
                .map(bus -> ResponseEntity.ok(ApiResponse.success(bus)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Bus not found")));
    }

    @PostMapping("/seats")
    public ResponseEntity<ApiResponse<SeatFullDTO>> createSeat(@RequestBody CreateSeatDTO seatRequest) {
        SeatFullDTO savedSeat = architectService.createSeat(seatRequest);
        return ResponseEntity.ok(ApiResponse.success(savedSeat));
    }

    @PatchMapping("/seats")
    public ResponseEntity<ApiResponse<SeatFullDTO>> updateSeat(@RequestBody UpdateSeatDTO seatRequest) {
        return architectService.updateSeat(seatRequest)
                .map(seat -> ResponseEntity.ok(ApiResponse.success(seat)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Seat not found")));
    }

    @DeleteMapping("/seats/{id}")
    public ResponseEntity<ApiResponse<SeatFullDTO>> deleteSeat(@PathVariable int id) {
        return architectService.deleteSeat(id)
                .map(seat -> ResponseEntity.ok(ApiResponse.success(seat)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Seat not found")));
    }

    @PostMapping("/seat-types")
    public ResponseEntity<ApiResponse<SeatTypeDTO>> createSeatType(@RequestBody CreateSeatTypeDTO seatTypeRequest) {
        SeatTypeDTO savedSeatType = architectService.createSeatType(seatTypeRequest);
        return ResponseEntity.ok(ApiResponse.success(savedSeatType));
    }

    @PatchMapping("/seat-types")
    public ResponseEntity<ApiResponse<SeatTypeDTO>> updateSeatType(@RequestBody UpdateSeatTypeDTO seatTypeRequest) {
        return architectService.updateSeatType(seatTypeRequest)
                .map(seatType -> ResponseEntity.ok(ApiResponse.success(seatType)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Seat type not found")));
    }

    @DeleteMapping("/seat-types/{id}")
    public ResponseEntity<ApiResponse<SeatTypeDTO>> deleteSeatType(@PathVariable int id) {
        return architectService.deleteSeatType(id)
                .map(seatType -> ResponseEntity.ok(ApiResponse.success(seatType)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Seat type not found")));
    }
}
