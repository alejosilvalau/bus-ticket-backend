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
import com.frro.bus.ticket.features.journey.mappers.LocationMapper;
import com.frro.bus.ticket.features.journey.mappers.TripMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/journeys/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final TripMapper tripMapper;
    private final LocationMapper locationMapper;

    @PostMapping
    public ResponseEntity<TripDTO> create(@RequestBody CreateTripDTO createTripDTO) {
        // TODO: Implement service to create a new trip
        // TripDTO createdTrip = tripService.createTrip(createTripDTO);
        // return ResponseEntity.ok(createdTrip);
        throw new UnsupportedOperationException("Unimplemented method 'createTrip'");
    }

    @PatchMapping("/trips/{id}/update")
    public ResponseEntity<TripDTO> updateTrip(@PathVariable int id, @RequestBody UpdateTripDTO updateTripDTO) {
        // TODO: Implement service to update a trip
        // Optional<TripDTO> updatedTrip = tripService.updateTrip(id,
        // updateTripDTO).map(tripMapper::toTripDTO);
        // return updatedTrip.map(ResponseEntity::ok)
        // .orElseGet(() -> ResponseEntity.notFound().build());
        throw new UnsupportedOperationException("Unimplemented method 'updateTrip'");
    }

    @DeleteMapping("/trips/{id}/delete")
    public ResponseEntity<TripDTO> deleteTrip(@PathVariable int id) {
        // TODO: Implement service to delete a trip (physical delete)
        // Optional<TripDTO> deletedTrip =
        // tripService.deleteTrip(id).map(tripMapper::toTripDTO);
        // return deletedTrip.map(ResponseEntity::ok)
        // .orElseGet(() -> ResponseEntity.notFound().build());
        throw new UnsupportedOperationException("Unimplemented method 'deleteTrip'");
    }

    @PatchMapping("/trips/{id}/logical-delete")
    public ResponseEntity<TripDTO> logicalDeleteTrip(@PathVariable int id) {
        // TODO: Implement service to logically delete a trip (soft delete)
        // Optional<TripDTO> updatedTrip =
        // tripService.logicalDeleteTrip(id).map(tripMapper::toTripDTO);
        // return updatedTrip.map(ResponseEntity::ok)
        // .orElseGet(() -> ResponseEntity.notFound().build());
        throw new UnsupportedOperationException("Unimplemented method 'logicalDeleteTrip'");
    }

    // Location Management

    @PostMapping("/locations/create")
    public ResponseEntity<LocationDTO> createLocation(@RequestBody CreateLocationDTO createLocationDTO) {
        // TODO: Implement service to create a new location
        // LocationDTO createdLocation =
        // locationService.createLocation(createLocationDTO);
        // return ResponseEntity.ok(createdLocation);
        throw new UnsupportedOperationException("Unimplemented method 'createLocation'");
    }

    @PatchMapping("/locations/{id}/update")
    public ResponseEntity<LocationDTO> updateLocation(@PathVariable int id,
            @RequestBody UpdateLocationDTO updateLocationDTO) {
        // TODO: Implement service to update a location
        // Optional<LocationDTO> updatedLocation = locationService.updateLocation(id,
        // updateLocationDTO).map(locationMapper::toLocationDTO);
        // return updatedLocation.map(ResponseEntity::ok)
        // .orElseGet(() -> ResponseEntity.notFound().build());
        throw new UnsupportedOperationException("Unimplemented method 'updateLocation'");
    }

    @DeleteMapping("/locations/{id}/delete")
    public ResponseEntity<LocationDTO> deleteLocation(@PathVariable int id) {
        // TODO: Implement service to delete a location (physical delete)
        // Optional<LocationDTO> deletedLocation =
        //
        // locationService.deleteLocation(id).map(locationMapper::toLocationDTO);
        // return deletedLocation.map(ResponseEntity::ok)
        // .orElseGet(() -> ResponseEntity.notFound().build());
        throw new UnsupportedOperationException("Unimplemented method 'deleteLocation'");
    }

    @PatchMapping("/locations/{id}/logical-delete")
    public ResponseEntity<LocationDTO> logicalDeleteLocation(@PathVariable int id) {
        // TODO: Implement service to logically delete a location (soft delete)
        // Optional<LocationDTO> updatedLocation = locationService.logicalDeleteLocation(id).map(locationMapper::toLocationDTO);
        // return updatedLocation.map(ResponseEntity::ok)
        //         .orElseGet(() -> ResponseEntity.notFound().build());
        th nsupportedOperationException("Unimplemented method 'logicalDeleteLocation'");
    }
}
