package com.frro.bus.ticket.features.fleet.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.common.dto.ApiResponse;
import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.SearchBusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatFullDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SearchSeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SearchSeatTypeDTO;
import com.frro.bus.ticket.features.fleet.services.availability.AvailabilityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/fleet/availability")
@RequiredArgsConstructor
public class AvailabilityController {
    private final AvailabilityService availabilityService;

    @GetMapping("/buses")
    public ResponseEntity<ApiResponse<Page<BusDTO>>> findAllBuses(Pageable pageable) {
        try {
            Page<BusDTO> buses = availabilityService.findAllBuses(pageable);
            return ResponseEntity.ok(ApiResponse.success("Buses retrieved successfully", buses));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to retrieve buses: " + e.getMessage()));
        }
    }

    @GetMapping("/buses/search")
    public ResponseEntity<ApiResponse<Page<BusDTO>>> searchBuses(@RequestBody SearchBusDTO searchCriteria, Pageable pageable) {
        try {
            Page<BusDTO> buses = availabilityService.searchBuses(searchCriteria, pageable);
            return ResponseEntity.ok(ApiResponse.success("Buses searched successfully", buses));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to search buses: " + e.getMessage()));
        }
    }

    @GetMapping("/buses/{id}")
    public ResponseEntity<ApiResponse<BusDTO>> findBusById(@PathVariable int id) {
        try {
            return availabilityService.findBusById(id)
                    .map(bus -> ResponseEntity.ok(ApiResponse.success("Bus retrieved successfully", bus)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Bus not found with id: " + id)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to retrieve bus: " + e.getMessage()));
        }
    }

    @GetMapping("/seats")
    public ResponseEntity<ApiResponse<Page<SeatFullDTO>>> findAllSeats(Pageable pageable) {
        try {
            Page<SeatFullDTO> seats = availabilityService.findAllSeats(pageable);
            return ResponseEntity.ok(ApiResponse.success("Seats retrieved successfully", seats));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to retrieve seats: " + e.getMessage()));
        }
    }

    @GetMapping("/seats/search")
    public ResponseEntity<ApiResponse<Page<SeatFullDTO>>> searchSeats(@RequestBody SearchSeatDTO searchCriteria, Pageable pageable) {
        try {
            Page<SeatFullDTO> seats = availabilityService.searchSeats(searchCriteria, pageable);
            return ResponseEntity.ok(ApiResponse.success("Seats searched successfully", seats));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to search seats: " + e.getMessage()));
        }
    }

    @GetMapping("/seats/{id}")
    public ResponseEntity<ApiResponse<SeatFullDTO>> findSeatById(@PathVariable int id) {
        try {
            return availabilityService.findSeatById(id)
                    .map(seat -> ResponseEntity.ok(ApiResponse.success("Seat retrieved successfully", seat)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Seat not found with id: " + id)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to retrieve seat: " + e.getMessage()));
        }
    }

    @GetMapping("/seat-types")
    public ResponseEntity<ApiResponse<Page<SeatTypeDTO>>> findAllSeatTypes(Pageable pageable) {
        try {
            Page<SeatTypeDTO> seatTypes = availabilityService.findAllSeatTypes(pageable);
            return ResponseEntity.ok(ApiResponse.success("Seat types retrieved successfully", seatTypes));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to retrieve seat types: " + e.getMessage()));
        }
    }

    @GetMapping("/seat-types/search")
    public ResponseEntity<ApiResponse<Page<SeatTypeDTO>>> searchSeatTypes(@RequestBody SearchSeatTypeDTO searchCriteria, Pageable pageable) {
        try {
            Page<SeatTypeDTO> seatTypes = availabilityService.searchSeatTypes(searchCriteria, pageable);
            return ResponseEntity.ok(ApiResponse.success("Seat types searched successfully", seatTypes));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to search seat types: " + e.getMessage()));
        }
    }

    @GetMapping("/seat-types/{id}")
    public ResponseEntity<ApiResponse<SeatTypeDTO>> findSeatTypeById(@PathVariable int id) {
        try {
            return availabilityService.findSeatTypeById(id)
                    .map(seatType -> ResponseEntity.ok(ApiResponse.success("Seat type retrieved successfully", seatType)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Seat type not found with id: " + id)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to retrieve seat type: " + e.getMessage()));
        }
    }
}
