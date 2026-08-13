package com.frro.bus.ticket.features.journey.controllers;

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
import com.frro.bus.ticket.features.journey.dtos.location.CreateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.UpdateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.CreateTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripFullDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.UpdateTripDTO;
import com.frro.bus.ticket.features.journey.services.inventory.InventoryService;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/journeys/inventory")
@AdminEndpoint
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/trips")
    public ResponseEntity<ApiResponse<TripFullDTO>> createTrip(@Valid @RequestBody CreateTripDTO tripRequest) {
        TripFullDTO savedTrip = inventoryService.createTrip(tripRequest);
        return ResponseEntity.ok(ApiResponse.success("Trip created successfully", savedTrip));
    }

    @PatchMapping("/trips")
    public ResponseEntity<ApiResponse<TripFullDTO>> updateTrip(@Valid @RequestBody UpdateTripDTO tripRequest) {
        TripFullDTO trip = inventoryService.updateTrip(tripRequest);
        return ResponseEntity.ok(ApiResponse.success("Trip updated successfully", trip));
    }

    @DeleteMapping("/trips/{id}")
    public ResponseEntity<ApiResponse<TripFullDTO>> deleteTrip(@PathVariable int id) {
        TripFullDTO trip = inventoryService.deleteTrip(id);
        return ResponseEntity.ok(ApiResponse.success("Trip deleted successfully", trip));
    }

    @PostMapping("/locations")
    public ResponseEntity<ApiResponse<LocationDTO>> createLocation(
            @Valid @RequestBody CreateLocationDTO locationRequest) {
        LocationDTO savedLocation = inventoryService.createLocation(locationRequest);
        return ResponseEntity.ok(ApiResponse.success("Location created successfully", savedLocation));
    }

    @PatchMapping("/locations")
    public ResponseEntity<ApiResponse<LocationDTO>> updateLocation(
            @Valid @RequestBody UpdateLocationDTO locationRequest) {
        LocationDTO location = inventoryService.updateLocation(locationRequest);
        return ResponseEntity.ok(ApiResponse.success("Location updated successfully", location));
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<ApiResponse<LocationDTO>> deleteLocation(@PathVariable int id) {
        LocationDTO location = inventoryService.deleteLocation(id);
        return ResponseEntity.ok(ApiResponse.success("Location deleted successfully", location));
    }
}
