package com.frro.bus.ticket.features.fleet.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.common.dto.ApiResponse;
import com.frro.bus.ticket.common.dto.PageResponse;
import com.frro.bus.ticket.common.security.endpointhelpers.PublicEndpoint;
import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.SearchBusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatFullDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SearchSeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SearchSeatTypeDTO;
import com.frro.bus.ticket.features.fleet.services.availability.AvailabilityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/fleet/availability")
@PublicEndpoint
@RequiredArgsConstructor
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    @GetMapping("/buses")
    public ResponseEntity<ApiResponse<PageResponse<BusDTO>>> findAllBuses(Pageable pageable) {
        PageResponse<BusDTO> buses = availabilityService.findAllBuses(pageable);
        return ResponseEntity.ok(ApiResponse.success("Buses retrieved successfully", buses));
    }

    @PostMapping("/buses/search")
    public ResponseEntity<ApiResponse<PageResponse<BusDTO>>> searchBuses(@Valid @RequestBody SearchBusDTO searchCriteria,
            Pageable pageable) {
        PageResponse<BusDTO> buses = availabilityService.searchBuses(searchCriteria, pageable);
        return ResponseEntity.ok(ApiResponse.success("Buses searched successfully", buses));
    }

    @GetMapping("/buses/{id}")
    public ResponseEntity<ApiResponse<BusDTO>> findBusById(@PathVariable int id) {
        BusDTO bus = availabilityService.findBusById(id);
        return ResponseEntity.ok(ApiResponse.success("Bus retrieved successfully", bus));
    }

    @GetMapping("/seats")
    public ResponseEntity<ApiResponse<PageResponse<SeatFullDTO>>> findAllSeats(Pageable pageable) {
        PageResponse<SeatFullDTO> seats = availabilityService.findAllSeats(pageable);
        return ResponseEntity.ok(ApiResponse.success("Seats retrieved successfully", seats));
    }

    @PostMapping("/seats/search")
    public ResponseEntity<ApiResponse<PageResponse<SeatFullDTO>>> searchSeats(@Valid @RequestBody SearchSeatDTO searchCriteria,
            Pageable pageable) {
        PageResponse<SeatFullDTO> seats = availabilityService.searchSeats(searchCriteria, pageable);
        return ResponseEntity.ok(ApiResponse.success("Seats searched successfully", seats));
    }

    @GetMapping("/seats/{id}")
    public ResponseEntity<ApiResponse<SeatFullDTO>> findSeatById(@PathVariable int id) {
        SeatFullDTO seat = availabilityService.findSeatById(id);
        return ResponseEntity.ok(ApiResponse.success("Seat retrieved successfully", seat));
    }

    @GetMapping("/seat-types")
    public ResponseEntity<ApiResponse<PageResponse<SeatTypeDTO>>> findAllSeatTypes(Pageable pageable) {
        PageResponse<SeatTypeDTO> seatTypes = availabilityService.findAllSeatTypes(pageable);
        return ResponseEntity.ok(ApiResponse.success("Seat types retrieved successfully", seatTypes));
    }

    @PostMapping("/seat-types/search")
    public ResponseEntity<ApiResponse<PageResponse<SeatTypeDTO>>> searchSeatTypes(
            @Valid @RequestBody SearchSeatTypeDTO searchCriteria, Pageable pageable) {
        PageResponse<SeatTypeDTO> seatTypes = availabilityService.searchSeatTypes(searchCriteria, pageable);
        return ResponseEntity.ok(ApiResponse.success("Seat types searched successfully", seatTypes));
    }

    @GetMapping("/seat-types/{id}")
    public ResponseEntity<ApiResponse<SeatTypeDTO>> findSeatTypeById(@PathVariable int id) {
        SeatTypeDTO seatType = availabilityService.findSeatTypeById(id);
        return ResponseEntity.ok(ApiResponse.success("Seat type retrieved successfully", seatType));
    }
}
