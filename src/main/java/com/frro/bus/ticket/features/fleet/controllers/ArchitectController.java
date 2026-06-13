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
        try {
            BusDTO savedBus = architectService.createBus(busRequest);
            return ResponseEntity.ok(ApiResponse.success("Bus created successfully", savedBus));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to create bus: " + e.getMessage()));
        }
    }

    @PatchMapping("/buses")
    public ResponseEntity<ApiResponse<BusDTO>> updateBus(@RequestBody UpdateBusDTO busRequest) {
        try {
            return architectService.updateBus(busRequest)
                    .map(bus -> ResponseEntity.ok(ApiResponse.success("Bus updated successfully", bus)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Bus not found with id: " + busRequest.id())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to update bus: " + e.getMessage()));
        }
    }

    @DeleteMapping("/buses/{id}")
    public ResponseEntity<ApiResponse<BusDTO>> deleteBus(@PathVariable int id) {
        try {
            return architectService.deleteBus(id)
                    .map(bus -> ResponseEntity.ok(ApiResponse.success("Bus deleted successfully", bus)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Bus not found with id: " + id)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to delete bus: " + e.getMessage()));
        }
    }

    @PostMapping("/seats")
    public ResponseEntity<ApiResponse<SeatFullDTO>> createSeat(@RequestBody CreateSeatDTO seatRequest) {
        try {
            SeatFullDTO savedSeat = architectService.createSeat(seatRequest);
            return ResponseEntity.ok(ApiResponse.success("Seat created successfully", savedSeat));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to create seat: " + e.getMessage()));
        }
    }

    @PatchMapping("/seats")
    public ResponseEntity<ApiResponse<SeatFullDTO>> updateSeat(@RequestBody UpdateSeatDTO seatRequest) {
        try {
            return architectService.updateSeat(seatRequest)
                    .map(seat -> ResponseEntity.ok(ApiResponse.success("Seat updated successfully", seat)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Seat not found with id: " + seatRequest.id())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to update seat: " + e.getMessage()));
        }
    }

    @DeleteMapping("/seats/{id}")
    public ResponseEntity<ApiResponse<SeatFullDTO>> deleteSeat(@PathVariable int id) {
        try {
            return architectService.deleteSeat(id)
                    .map(seat -> ResponseEntity.ok(ApiResponse.success("Seat deleted successfully", seat)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Seat not found with id: " + id)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to delete seat: " + e.getMessage()));
        }
    }

    @PostMapping("/seat-types")
    public ResponseEntity<ApiResponse<SeatTypeDTO>> createSeatType(@RequestBody CreateSeatTypeDTO seatTypeRequest) {
        try {
            SeatTypeDTO savedSeatType = architectService.createSeatType(seatTypeRequest);
            return ResponseEntity.ok(ApiResponse.success("Seat type created successfully", savedSeatType));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to create seat type: " + e.getMessage()));
        }
    }

    @PatchMapping("/seat-types")
    public ResponseEntity<ApiResponse<SeatTypeDTO>> updateSeatType(@RequestBody UpdateSeatTypeDTO seatTypeRequest) {
        try {
            return architectService.updateSeatType(seatTypeRequest)
                    .map(seatType -> ResponseEntity.ok(ApiResponse.success("Seat type updated successfully", seatType)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Seat type not found with id: " + seatTypeRequest.id())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to update seat type: " + e.getMessage()));
        }
    }

    @DeleteMapping("/seat-types/{id}")
    public ResponseEntity<ApiResponse<SeatTypeDTO>> deleteSeatType(@PathVariable int id) {
        try {
            return architectService.deleteSeatType(id)
                    .map(seatType -> ResponseEntity.ok(ApiResponse.success("Seat type deleted successfully", seatType)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Seat type not found with id: " + id)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to delete seat type: " + e.getMessage()));
        }
    }
}
