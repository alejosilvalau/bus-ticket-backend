package com.frro.bus.ticket.features.journey.controllers;

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
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripFullDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.SearchTripDTO;
import com.frro.bus.ticket.features.journey.dtos.location.SearchLocationDTO;
import com.frro.bus.ticket.features.journey.services.catalog.CatalogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/journeys/catalog")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;

    @GetMapping("/trips")
    public ResponseEntity<ApiResponse<List<TripFullDTO>>> findAllTrips(Pageable pageable) {
        Page<TripFullDTO> trips = catalogService.findAllTrips(pageable);
        return ResponseEntity.ok(ApiResponse.success(trips.getContent(), PaginationMeta.fromPage(trips)));
    }

    @GetMapping("/trips/search")
    public ResponseEntity<ApiResponse<List<TripFullDTO>>> searchTrips(@RequestBody SearchTripDTO searchCriteria, Pageable pageable) {
        Page<TripFullDTO> trips = catalogService.searchTrips(searchCriteria, pageable);
        return ResponseEntity.ok(ApiResponse.success(trips.getContent(), PaginationMeta.fromPage(trips)));
    }

    @GetMapping("/trips/{id}")
    public ResponseEntity<ApiResponse<TripFullDTO>> findTripById(@PathVariable int id) {
        return catalogService.findTripById(id)
                .map(trip -> ResponseEntity.ok(ApiResponse.success(trip)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Trip not found")));
    }

    @GetMapping("/locations")
    public ResponseEntity<ApiResponse<List<LocationDTO>>> findAllLocations(Pageable pageable) {
        Page<LocationDTO> locations = catalogService.findAllLocations(pageable);
        return ResponseEntity.ok(ApiResponse.success(locations.getContent(), PaginationMeta.fromPage(locations)));
    }

    @GetMapping("/locations/search")
    public ResponseEntity<ApiResponse<List<LocationDTO>>> searchLocations(@RequestBody SearchLocationDTO searchCriteria, Pageable pageable) {
        Page<LocationDTO> locations = catalogService.searchLocations(searchCriteria, pageable);
        return ResponseEntity.ok(ApiResponse.success(locations.getContent(), PaginationMeta.fromPage(locations)));
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<ApiResponse<LocationDTO>> findLocationById(@PathVariable int id) {
        return catalogService.findLocationById(id)
                .map(location -> ResponseEntity.ok(ApiResponse.success(location)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Location not found")));
    }
}
