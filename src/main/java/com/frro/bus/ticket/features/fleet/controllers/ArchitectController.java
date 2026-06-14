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
import com.frro.bus.ticket.common.security.endpointhelpers.AdminEndpoint;
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

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/fleet/architect")
@AdminEndpoint
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class ArchitectController {

    private final ArchitectService architectService;

    @PostMapping("/buses")
    public ResponseEntity<ApiResponse<BusDTO>> createBus(@Valid @RequestBody CreateBusDTO busRequest) {
        BusDTO savedBus = architectService.createBus(busRequest);
        return ResponseEntity.ok(ApiResponse.success("Bus created successfully", savedBus));
    }

    @PatchMapping("/buses")
    public ResponseEntity<ApiResponse<BusDTO>> updateBus(@Valid @RequestBody UpdateBusDTO busRequest) {
        BusDTO bus = architectService.updateBus(busRequest);
        return ResponseEntity.ok(ApiResponse.success("Bus updated successfully", bus));
    }

    @DeleteMapping("/buses/{id}")
    public ResponseEntity<ApiResponse<BusDTO>> deleteBus(@PathVariable int id) {
        BusDTO bus = architectService.deleteBus(id);
        return ResponseEntity.ok(ApiResponse.success("Bus deleted successfully", bus));
    }

    @PostMapping("/seats")
    public ResponseEntity<ApiResponse<SeatFullDTO>> createSeat(@Valid @RequestBody CreateSeatDTO seatRequest) {
        SeatFullDTO savedSeat = architectService.createSeat(seatRequest);
        return ResponseEntity.ok(ApiResponse.success("Seat created successfully", savedSeat));
    }

    @PatchMapping("/seats")
    public ResponseEntity<ApiResponse<SeatFullDTO>> updateSeat(@Valid @RequestBody UpdateSeatDTO seatRequest) {
        SeatFullDTO seat = architectService.updateSeat(seatRequest);
        return ResponseEntity.ok(ApiResponse.success("Seat updated successfully", seat));
    }

    @DeleteMapping("/seats/{id}")
    public ResponseEntity<ApiResponse<SeatFullDTO>> deleteSeat(@PathVariable int id) {
        SeatFullDTO seat = architectService.deleteSeat(id);
        return ResponseEntity.ok(ApiResponse.success("Seat deleted successfully", seat));
    }

    @PostMapping("/seat-types")
    public ResponseEntity<ApiResponse<SeatTypeDTO>> createSeatType(
            @Valid @RequestBody CreateSeatTypeDTO seatTypeRequest) {
        SeatTypeDTO savedSeatType = architectService.createSeatType(seatTypeRequest);
        return ResponseEntity.ok(ApiResponse.success("Seat type created successfully", savedSeatType));
    }

    @PatchMapping("/seat-types")
    public ResponseEntity<ApiResponse<SeatTypeDTO>> updateSeatType(
            @Valid @RequestBody UpdateSeatTypeDTO seatTypeRequest) {
        SeatTypeDTO seatType = architectService.updateSeatType(seatTypeRequest);
        return ResponseEntity.ok(ApiResponse.success("Seat type updated successfully", seatType));
    }

    @DeleteMapping("/seat-types/{id}")
    public ResponseEntity<ApiResponse<SeatTypeDTO>> deleteSeatType(@PathVariable int id) {
        SeatTypeDTO seatType = architectService.deleteSeatType(id);
        return ResponseEntity.ok(ApiResponse.success("Seat type deleted successfully", seatType));
    }
}
