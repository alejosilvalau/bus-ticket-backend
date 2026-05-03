package com.frro.bus.ticket.features.journey.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.features.journey.dtos.location.CreateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.UpdateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.CreateTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.UpdateTripDTO;
import com.frro.bus.ticket.features.journey.services.inventory.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/journeys/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/trips")
    public ResponseEntity<TripDTO> createTrip(@RequestBody CreateTripDTO tripRequest) {
        TripDTO savedTrip = inventoryService.createTrip(tripRequest);
        return ResponseEntity.ok(savedTrip);
    }

    @PostMapping("/locations")
    public ResponseEntity<LocationDTO> createLocation(@RequestBody CreateLocationDTO locationRequest) {
        LocationDTO savedLocation = inventoryService.createLocation(locationRequest);
        return ResponseEntity.ok(savedLocation);
    }

    @PatchMapping("/trips")
    public ResponseEntity<TripDTO> updateTrip(@RequestBody UpdateTripDTO tripRequest) {
        Optional<TripDTO> updatedTrip = inventoryService.updateTrip(tripRequest);
        return updatedTrip.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/locations")
    public ResponseEntity<LocationDTO> updateLocation(@RequestBody UpdateLocationDTO locationRequest) {
        Optional<LocationDTO> updatedLocation = inventoryService.updateLocation(locationRequest);
        return updatedLocation.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/trips/{id}")
    public ResponseEntity<TripDTO> deleteTrip(@PathVariable int id) {
        Optional<TripDTO> deletedTrip = inventoryService.deleteTrip(id);
        return deletedTrip.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<LocationDTO> deleteLocation(@PathVariable int id) {
        Optional<LocationDTO> deletedLocation = inventoryService.deleteLocation(id);
        return deletedLocation.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
