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
        try {
            TripFullDTO savedTrip = inventoryService.createTrip(tripRequest);
            return ResponseEntity.ok(ApiResponse.success("Trip created successfully", savedTrip));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to create trip: " + e.getMessage()));
        }
    }

    @PostMapping("/locations")
    public ResponseEntity<ApiResponse<LocationDTO>> createLocation(@RequestBody CreateLocationDTO locationRequest) {
        try {
            LocationDTO savedLocation = inventoryService.createLocation(locationRequest);
            return ResponseEntity.ok(ApiResponse.success("Location created successfully", savedLocation));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to create location: " + e.getMessage()));
        }
    }

    @PatchMapping("/trips")
    public ResponseEntity<ApiResponse<TripFullDTO>> updateTrip(@RequestBody UpdateTripDTO tripRequest) {
        try {
            return inventoryService.updateTrip(tripRequest)
                    .map(trip -> ResponseEntity.ok(ApiResponse.success("Trip updated successfully", trip)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Trip not found with id: " + tripRequest.id())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to update trip: " + e.getMessage()));
        }
    }

    @PatchMapping("/locations")
    public ResponseEntity<ApiResponse<LocationDTO>> updateLocation(@RequestBody UpdateLocationDTO locationRequest) {
        try {
            return inventoryService.updateLocation(locationRequest)
                    .map(location -> ResponseEntity.ok(ApiResponse.success("Location updated successfully", location)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Location not found with id: " + locationRequest.id())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to update location: " + e.getMessage()));
        }
    }

    @DeleteMapping("/trips/{id}")
    public ResponseEntity<ApiResponse<TripFullDTO>> deleteTrip(@PathVariable int id) {
        try {
            return inventoryService.deleteTrip(id)
                    .map(trip -> ResponseEntity.ok(ApiResponse.success("Trip deleted successfully", trip)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Trip not found with id: " + id)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to delete trip: " + e.getMessage()));
        }
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<ApiResponse<LocationDTO>> deleteLocation(@PathVariable int id) {
        try {
            return inventoryService.deleteLocation(id)
                    .map(location -> ResponseEntity.ok(ApiResponse.success("Location deleted successfully", location)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Location not found with id: " + id)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to delete location: " + e.getMessage()));
        }
    }
}
