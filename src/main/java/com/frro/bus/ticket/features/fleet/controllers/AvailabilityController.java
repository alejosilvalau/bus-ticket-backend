package com.frro.bus.ticket.features.fleet.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.common.dto.ApiResponse;
import com.frro.bus.ticket.common.dto.PaginationMeta;
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
    public ResponseEntity<ApiResponse<List<BusDTO>>> findAllBuses(Pageable pageable) {
        Page<BusDTO> buses = availabilityService.findAllBuses(pageable);
        return ResponseEntity.ok(ApiResponse.success(buses.getContent(), PaginationMeta.fromPage(buses)));
    }

    @GetMapping("/buses/search")
    public ResponseEntity<ApiResponse<List<BusDTO>>> searchBuses(@RequestBody SearchBusDTO searchCriteria, Pageable pageable) {
        Page<BusDTO> buses = availabilityService.searchBuses(searchCriteria, pageable);
        return ResponseEntity.ok(ApiResponse.success(buses.getContent(), PaginationMeta.fromPage(buses)));
    }

    @GetMapping("/buses/{id}")
    public ResponseEntity<ApiResponse<BusDTO>> findBusById(@PathVariable int id) {
        return availabilityService.findBusById(id)
                .map(bus -> ResponseEntity.ok(ApiResponse.success(bus)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Bus not found")));
    }

    @GetMapping("/seats")
    public ResponseEntity<ApiResponse<List<SeatFullDTO>>> findAllSeats(Pageable pageable) {
        Page<SeatFullDTO> seats = availabilityService.findAllSeats(pageable);
        return ResponseEntity.ok(ApiResponse.success(seats.getContent(), PaginationMeta.fromPage(seats)));
    }

    @GetMapping("/seats/search")
    public ResponseEntity<ApiResponse<List<SeatFullDTO>>> searchSeats(@RequestBody SearchSeatDTO searchCriteria, Pageable pageable) {
        Page<SeatFullDTO> seats = availabilityService.searchSeats(searchCriteria, pageable);
        return ResponseEntity.ok(ApiResponse.success(seats.getContent(), PaginationMeta.fromPage(seats)));
    }

    @GetMapping("/seats/{id}")
    public ResponseEntity<ApiResponse<SeatFullDTO>> findSeatById(@PathVariable int id) {
        return availabilityService.findSeatById(id)
                .map(seat -> ResponseEntity.ok(ApiResponse.success(seat)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Seat not found")));
    }

    @GetMapping("/seat-types")
    public ResponseEntity<ApiResponse<List<SeatTypeDTO>>> findAllSeatTypes(Pageable pageable) {
        Page<SeatTypeDTO> seatTypes = availabilityService.findAllSeatTypes(pageable);
        return ResponseEntity.ok(ApiResponse.success(seatTypes.getContent(), PaginationMeta.fromPage(seatTypes)));
    }

    @GetMapping("/seat-types/search")
    public ResponseEntity<ApiResponse<List<SeatTypeDTO>>> searchSeatTypes(@RequestBody SearchSeatTypeDTO searchCriteria, Pageable pageable) {
        Page<SeatTypeDTO> seatTypes = availabilityService.searchSeatTypes(searchCriteria, pageable);
        return ResponseEntity.ok(ApiResponse.success(seatTypes.getContent(), PaginationMeta.fromPage(seatTypes)));
    }

    @GetMapping("/seat-types/{id}")
    public ResponseEntity<ApiResponse<SeatTypeDTO>> findSeatTypeById(@PathVariable int id) {
        return availabilityService.findSeatTypeById(id)
                .map(seatType -> ResponseEntity.ok(ApiResponse.success(seatType)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Seat type not found")));
    }
}
