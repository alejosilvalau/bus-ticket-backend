package com.frro.bus.ticket.features.journey.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;
import com.frro.bus.ticket.features.journey.entities.Location;
import com.frro.bus.ticket.features.journey.entities.Trip;
import com.frro.bus.ticket.features.journey.mappers.LocationMapper;
import com.frro.bus.ticket.features.journey.mappers.TripMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/journeys")
@RequiredArgsConstructor
public class JourneyController {
    private final TripMapper tripMapper;
    private final LocationMapper locationMapper;

    @GetMapping("/trips")
    public ResponseEntity<List<TripDTO>> getAllTrips() {
        // TODO: Implement service to fetch all trips from database
        // List<Trip> trips = tripService.getAllTrips();
        // return ResponseEntity.ok(trips.stream().map(tripMapper::toTripDTO).toList());
        throw new UnsupportedOperationException("Unimplemented method 'getAllTrips'");
    }

    @GetMapping("/trips/{id}")
    public ResponseEntity<TripDTO> getTripById(@PathVariable int id) {
        // TODO: Implement service to fetch trip by id
        // Optional<TripDTO> trip = tripService.getTripById(id).map(tripMapper::toTripDTO);
        // return trip.map(ResponseEntity::ok)
        //         .orElseGet(() -> ResponseEntity.notFound().build());
        throw new UnsupportedOperationException("Unimplemented method 'getTripById'");
    }

    @PostMapping("/trips/search")
    public ResponseEntity<List<TripDTO>> searchTrips(@RequestBody Trip searchCriteria) {
        // TODO: Implement service to search trips by criteria (departure location, arrival location, date, etc.)
        // List<Trip> trips = tripService.searchTrips(searchCriteria);
        // return ResponseEntity.ok(trips.stream().map(tripMapper::toTripDTO).toList());
        throw new UnsupportedOperationException("Unimplemented method 'searchTrips'");
    }

    @GetMapping("/locations")
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        // TODO: Implement service to fetch all locations from database
        // List<Location> locations = locationService.getAllLocations();
        // return ResponseEntity.ok(locations.stream().map(locationMapper::toLocationDTO).toList());
        throw new UnsupportedOperationException("Unimplemented method 'getAllLocations'");
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable int id) {
        // TODO: Implement service to fetch location by id
        // Optional<LocationDTO> location = locationService.getLocationById(id).map(locationMapper::toLocationDTO);
        // return location.map(ResponseEntity::ok)
        //         .orElseGet(() -> ResponseEntity.notFound().build());
        throw new UnsupportedOperationException("Unimplemented method 'getLocationById'");
    }
}
