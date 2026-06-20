package com.frro.bus.ticket.features.journey.controllers;

import java.util.List;

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
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatAvailabilityDTO;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripFullDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.SearchTripDTO;
import com.frro.bus.ticket.features.journey.dtos.location.SearchLocationDTO;
import com.frro.bus.ticket.features.journey.services.catalog.CatalogService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/journeys/catalog")
@PublicEndpoint
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping("/trips")
    public ResponseEntity<ApiResponse<PageResponse<TripFullDTO>>> findAllTrips(Pageable pageable) {
        PageResponse<TripFullDTO> trips = catalogService.findAllTrips(pageable);
        return ResponseEntity.ok(ApiResponse.success("Trips retrieved successfully", trips));
    }

    @PostMapping("/trips/search")
    public ResponseEntity<ApiResponse<PageResponse<TripFullDTO>>> searchTrips(
            @Valid @RequestBody SearchTripDTO searchCriteria,
            Pageable pageable) {
        PageResponse<TripFullDTO> trips = catalogService.searchTrips(searchCriteria, pageable);
        return ResponseEntity.ok(ApiResponse.success("Trips searched successfully", trips));
    }

    @GetMapping("/trips/available")
    public ResponseEntity<ApiResponse<PageResponse<TripFullDTO>>> findAllAvailableTrips(Pageable pageable) {
        PageResponse<TripFullDTO> trips = catalogService.findAllAvailableTrips(pageable);
        return ResponseEntity.ok(ApiResponse.success("Available trips retrieved successfully", trips));
    }

    @PostMapping("/trips/available/search")
    public ResponseEntity<ApiResponse<PageResponse<TripFullDTO>>> searchAvailableTrips(
            @Valid @RequestBody SearchTripDTO searchCriteria, Pageable pageable) {
        PageResponse<TripFullDTO> trips = catalogService.searchAvailableTrips(searchCriteria, pageable);
        return ResponseEntity.ok(ApiResponse.success("Available trips searched successfully", trips));
    }

    @GetMapping("/trips/{id}/available/seats")
    public ResponseEntity<ApiResponse<List<SeatAvailabilityDTO>>> findAvailableSeatsByTripId(@PathVariable int id) {
        List<SeatAvailabilityDTO> seats = catalogService.findAvailableSeatsByTripId(id);
        return ResponseEntity.ok(ApiResponse.success("Seats retrieved successfully", seats));
    }

    @GetMapping("/trips/{id}")
    public ResponseEntity<ApiResponse<TripFullDTO>> findTripById(@PathVariable int id) {
        TripFullDTO trip = catalogService.findTripById(id);
        return ResponseEntity.ok(ApiResponse.success("Trip retrieved successfully", trip));
    }

    @GetMapping("/locations")
    public ResponseEntity<ApiResponse<PageResponse<LocationDTO>>> findAllLocations(Pageable pageable) {
        PageResponse<LocationDTO> locations = catalogService.findAllLocations(pageable);
        return ResponseEntity.ok(ApiResponse.success("Locations retrieved successfully", locations));
    }

    @PostMapping("/locations/search")
    public ResponseEntity<ApiResponse<PageResponse<LocationDTO>>> searchLocations(
            @Valid @RequestBody SearchLocationDTO searchCriteria, Pageable pageable) {
        PageResponse<LocationDTO> locations = catalogService.searchLocations(searchCriteria, pageable);
        return ResponseEntity.ok(ApiResponse.success("Locations searched successfully", locations));
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<ApiResponse<LocationDTO>> findLocationById(@PathVariable int id) {
        LocationDTO location = catalogService.findLocationById(id);
        return ResponseEntity.ok(ApiResponse.success("Location retrieved successfully", location));
    }
}
