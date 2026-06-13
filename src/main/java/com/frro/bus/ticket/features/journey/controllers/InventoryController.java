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
import com.frro.bus.ticket.features.journey.dtos.location.CreateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.UpdateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.CreateTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripFullDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.UpdateTripDTO;
import com.frro.bus.ticket.features.journey.services.inventory.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/journeys/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/trips")
    public ResponseEntity<ApiResponse<TripFullDTO>> createTrip(@RequestBody CreateTripDTO tripRequest) {
        TripFullDTO savedTrip = inventoryService.createTrip(tripRequest);
        return ResponseEntity.ok(ApiResponse.success(savedTrip));
    }

    @PostMapping("/locations")
    public ResponseEntity<ApiResponse<LocationDTO>> createLocation(@RequestBody CreateLocationDTO locationRequest) {
        LocationDTO savedLocation = inventoryService.createLocation(locationRequest);
        return ResponseEntity.ok(ApiResponse.success(savedLocation));
    }

    @PatchMapping("/trips")
    public ResponseEntity<ApiResponse<TripFullDTO>> updateTrip(@RequestBody UpdateTripDTO tripRequest) {
        return inventoryService.updateTrip(tripRequest)
                .map(trip -> ResponseEntity.ok(ApiResponse.success(trip)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Trip not found")));
    }

    @PatchMapping("/locations")
    public ResponseEntity<ApiResponse<LocationDTO>> updateLocation(@RequestBody UpdateLocationDTO locationRequest) {
        return inventoryService.updateLocation(locationRequest)
                .map(location -> ResponseEntity.ok(ApiResponse.success(location)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Location not found")));
    }

    @DeleteMapping("/trips/{id}")
    public ResponseEntity<ApiResponse<TripFullDTO>> deleteTrip(@PathVariable int id) {
        return inventoryService.deleteTrip(id)
                .map(trip -> ResponseEntity.ok(ApiResponse.success(trip)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Trip not found")));
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<ApiResponse<LocationDTO>> deleteLocation(@PathVariable int id) {
        return inventoryService.deleteLocation(id)
                .map(location -> ResponseEntity.ok(ApiResponse.success(location)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Location not found")));
    }
}
