package com.frro.bus.ticket.features.journey.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.common.dto.ApiResponse;
import com.frro.bus.ticket.common.security.endpointhelpers.PublicEndpoint;
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
    public ResponseEntity<ApiResponse<Page<TripFullDTO>>> findAllTrips(Pageable pageable) {
        Page<TripFullDTO> trips = catalogService.findAllTrips(pageable);
        return ResponseEntity.ok(ApiResponse.success("Trips retrieved successfully", trips));
    }

    @PostMapping("/trips/search")
    public ResponseEntity<ApiResponse<Page<TripFullDTO>>> searchTrips(@Valid @RequestBody SearchTripDTO searchCriteria,
            Pageable pageable) {
        Page<TripFullDTO> trips = catalogService.searchTrips(searchCriteria, pageable);
        return ResponseEntity.ok(ApiResponse.success("Trips searched successfully", trips));
    }

    @GetMapping("/trips/{id}")
    public ResponseEntity<ApiResponse<TripFullDTO>> findTripById(@PathVariable int id) {
        TripFullDTO trip = catalogService.findTripById(id);
        return ResponseEntity.ok(ApiResponse.success("Trip retrieved successfully", trip));
    }

    @GetMapping("/locations")
    public ResponseEntity<ApiResponse<Page<LocationDTO>>> findAllLocations(Pageable pageable) {
        Page<LocationDTO> locations = catalogService.findAllLocations(pageable);
        return ResponseEntity.ok(ApiResponse.success("Locations retrieved successfully", locations));
    }

    @PostMapping("/locations/search")
    public ResponseEntity<ApiResponse<Page<LocationDTO>>> searchLocations(
            @Valid @RequestBody SearchLocationDTO searchCriteria, Pageable pageable) {
        Page<LocationDTO> locations = catalogService.searchLocations(searchCriteria, pageable);
        return ResponseEntity.ok(ApiResponse.success("Locations searched successfully", locations));
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<ApiResponse<LocationDTO>> findLocationById(@PathVariable int id) {
        LocationDTO location = catalogService.findLocationById(id);
        return ResponseEntity.ok(ApiResponse.success("Location retrieved successfully", location));
    }
}
