package com.frro.bus.ticket.features.fleet.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/fleet/architect")
@AdminEndpoint
@RequiredArgsConstructor
public class ArchitectController {

    private static final Logger log = LoggerFactory.getLogger(ArchitectController.class);

    private final ArchitectService architectService;

    @PostMapping("/buses")
    public ResponseEntity<ApiResponse<BusDTO>> createBus(@Valid @RequestBody CreateBusDTO busRequest) {
        try {
            BusDTO savedBus = architectService.createBus(busRequest);
            return ResponseEntity.ok(ApiResponse.success("Bus created successfully", savedBus));
        } catch (Exception e) {
            log.error("Failed to create bus", e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("Failed to create bus. Please try again later."));
        }
    }

    @PatchMapping("/buses")
    public ResponseEntity<ApiResponse<BusDTO>> updateBus(@Valid @RequestBody UpdateBusDTO busRequest) {
        try {
            return architectService.updateBus(busRequest)
                    .map(bus -> ResponseEntity.ok(ApiResponse.success("Bus updated successfully", bus)))
                    .orElseGet(() -> ResponseEntity.status(404)
                            .body(ApiResponse.error("Bus not found with id: " + busRequest.id())));
        } catch (Exception e) {
            log.error("Failed to update bus with id: {}", busRequest.id(), e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("Failed to update bus. Please try again later."));
        }
    }

    @DeleteMapping("/buses/{id}")
    public ResponseEntity<ApiResponse<BusDTO>> deleteBus(@PathVariable int id) {
        try {
            return architectService.deleteBus(id)
                    .map(bus -> ResponseEntity.ok(ApiResponse.success("Bus deleted successfully", bus)))
                    .orElseGet(
                            () -> ResponseEntity.status(404).body(ApiResponse.error("Bus not found with id: " + id)));
        } catch (Exception e) {
            log.error("Failed to delete bus with id: {}", id, e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("Failed to delete bus. Please try again later."));
        }
    }

    @PostMapping("/seats")
    public ResponseEntity<ApiResponse<SeatFullDTO>> createSeat(@Valid @RequestBody CreateSeatDTO seatRequest) {
        try {
            SeatFullDTO savedSeat = architectService.createSeat(seatRequest);
            return ResponseEntity.ok(ApiResponse.success("Seat created successfully", savedSeat));
        } catch (Exception e) {
            log.error("Failed to create seat", e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("Failed to create seat. Please try again later."));
        }
    }

    @PatchMapping("/seats")
    public ResponseEntity<ApiResponse<SeatFullDTO>> updateSeat(@Valid @RequestBody UpdateSeatDTO seatRequest) {
        try {
            return architectService.updateSeat(seatRequest)
                    .map(seat -> ResponseEntity.ok(ApiResponse.success("Seat updated successfully", seat)))
                    .orElseGet(() -> ResponseEntity.status(404)
                            .body(ApiResponse.error("Seat not found with id: " + seatRequest.id())));
        } catch (Exception e) {
            log.error("Failed to update seat with id: {}", seatRequest.id(), e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("Failed to update seat. Please try again later."));
        }
    }

    @DeleteMapping("/seats/{id}")
    public ResponseEntity<ApiResponse<SeatFullDTO>> deleteSeat(@PathVariable int id) {
        try {
            return architectService.deleteSeat(id)
                    .map(seat -> ResponseEntity.ok(ApiResponse.success("Seat deleted successfully", seat)))
                    .orElseGet(
                            () -> ResponseEntity.status(404).body(ApiResponse.error("Seat not found with id: " + id)));
        } catch (Exception e) {
            log.error("Failed to delete seat with id: {}", id, e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("Failed to delete seat. Please try again later."));
        }
    }

    @PostMapping("/seat-types")
    public ResponseEntity<ApiResponse<SeatTypeDTO>> createSeatType(
            @Valid @RequestBody CreateSeatTypeDTO seatTypeRequest) {
        try {
            SeatTypeDTO savedSeatType = architectService.createSeatType(seatTypeRequest);
            return ResponseEntity.ok(ApiResponse.success("Seat type created successfully", savedSeatType));
        } catch (Exception e) {
            log.error("Failed to create seat type", e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("Failed to create seat type. Please try again later."));
        }
    }

    @PatchMapping("/seat-types")
    public ResponseEntity<ApiResponse<SeatTypeDTO>> updateSeatType(
            @Valid @RequestBody UpdateSeatTypeDTO seatTypeRequest) {
        try {
            return architectService.updateSeatType(seatTypeRequest)
                    .map(seatType -> ResponseEntity.ok(ApiResponse.success("Seat type updated successfully", seatType)))
                    .orElseGet(() -> ResponseEntity.status(404)
                            .body(ApiResponse.error("Seat type not found with id: " + seatTypeRequest.id())));
        } catch (Exception e) {
            log.error("Failed to update seat type with id: {}", seatTypeRequest.id(), e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("Failed to update seat type. Please try again later."));
        }
    }

    @DeleteMapping("/seat-types/{id}")
    public ResponseEntity<ApiResponse<SeatTypeDTO>> deleteSeatType(@PathVariable int id) {
        try {
            return architectService.deleteSeatType(id)
                    .map(seatType -> ResponseEntity.ok(ApiResponse.success("Seat type deleted successfully", seatType)))
                    .orElseGet(() -> ResponseEntity.status(404)
                            .body(ApiResponse.error("Seat type not found with id: " + id)));
        } catch (Exception e) {
            log.error("Failed to delete seat type with id: {}", id, e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("Failed to delete seat type. Please try again later."));
        }
    }
}
