package com.frro.bus.ticket.features.journey.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.common.dto.ApiResponse;
import com.frro.bus.ticket.common.security.endpointhelpers.AdminEndpoint;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripFullDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.SearchTripDTO;
import com.frro.bus.ticket.features.journey.dtos.location.SearchLocationDTO;
import com.frro.bus.ticket.features.journey.services.catalog.CatalogService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/journeys/catalog")
@AdminEndpoint
@RequiredArgsConstructor
public class CatalogController {

    private static final Logger log = LoggerFactory.getLogger(CatalogController.class);

    private final CatalogService catalogService;

    @GetMapping("/trips")
    public ResponseEntity<ApiResponse<Page<TripFullDTO>>> findAllTrips(Pageable pageable) {
        try {
            Page<TripFullDTO> trips = catalogService.findAllTrips(pageable);
            return ResponseEntity.ok(ApiResponse.success("Trips retrieved successfully", trips));
        } catch (Exception e) {
            log.error("Failed to retrieve trips", e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("Failed to retrieve trips. Please try again later."));
        }
    }

    @GetMapping("/trips/search")
    public ResponseEntity<ApiResponse<Page<TripFullDTO>>> searchTrips(@Valid @RequestBody SearchTripDTO searchCriteria,
            Pageable pageable) {
        try {
            Page<TripFullDTO> trips = catalogService.searchTrips(searchCriteria, pageable);
            return ResponseEntity.ok(ApiResponse.success("Trips searched successfully", trips));
        } catch (Exception e) {
            log.error("Failed to search trips", e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("Failed to search trips. Please try again later."));
        }
    }

    @GetMapping("/trips/{id}")
    public ResponseEntity<ApiResponse<TripFullDTO>> findTripById(@PathVariable int id) {
        try {
            return catalogService.findTripById(id)
                    .map(trip -> ResponseEntity.ok(ApiResponse.success("Trip retrieved successfully", trip)))
                    .orElseGet(
                            () -> ResponseEntity.status(404).body(ApiResponse.error("Trip not found with id: " + id)));
        } catch (Exception e) {
            log.error("Failed to retrieve trip with id: {}", id, e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("Failed to retrieve trip. Please try again later."));
        }
    }

    @GetMapping("/locations")
    public ResponseEntity<ApiResponse<Page<LocationDTO>>> findAllLocations(Pageable pageable) {
        try {
            Page<LocationDTO> locations = catalogService.findAllLocations(pageable);
            return ResponseEntity.ok(ApiResponse.success("Locations retrieved successfully", locations));
        } catch (Exception e) {
            log.error("Failed to retrieve locations", e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("Failed to retrieve locations. Please try again later."));
        }
    }

    @GetMapping("/locations/search")
    public ResponseEntity<ApiResponse<Page<LocationDTO>>> searchLocations(
            @Valid @RequestBody SearchLocationDTO searchCriteria, Pageable pageable) {
        try {
            Page<LocationDTO> locations = catalogService.searchLocations(searchCriteria, pageable);
            return ResponseEntity.ok(ApiResponse.success("Locations searched successfully", locations));
        } catch (Exception e) {
            log.error("Failed to search locations", e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("Failed to search locations. Please try again later."));
        }
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<ApiResponse<LocationDTO>> findLocationById(@PathVariable int id) {
        try {
            return catalogService.findLocationById(id)
                    .map(location -> ResponseEntity
                            .ok(ApiResponse.success("Location retrieved successfully", location)))
                    .orElseGet(() -> ResponseEntity.status(404)
                            .body(ApiResponse.error("Location not found with id: " + id)));
        } catch (Exception e) {
            log.error("Failed to retrieve location with id: {}", id, e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("Failed to retrieve location. Please try again later."));
        }
    }
}
