package com.frro.bus.ticket.features.journey.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.SearchLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripFullDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.SearchTripDTO;
import com.frro.bus.ticket.features.journey.services.catalog.CatalogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/journeys/catalog")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;

    @GetMapping("/trips")
    public ResponseEntity<List<TripFullDTO>> findAllTrips() {
        List<TripFullDTO> trips = catalogService.findAllTrips();
        System.out.println("----------------------------------------------");
        System.out.println(trips);
        System.out.println("----------------------------------------------");
        return ResponseEntity.ok(trips);
    }

    @GetMapping("/locations")
    public ResponseEntity<List<LocationDTO>> findAllLocations() {
        List<LocationDTO> locations = catalogService.findAllLocations();
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/trips/{id}")
    public ResponseEntity<TripFullDTO> findTripById(@PathVariable int id) {
        Optional<TripFullDTO> trip = catalogService.findTripById(id);
        return trip.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<LocationDTO> findLocationById(@PathVariable int id) {
        Optional<LocationDTO> location = catalogService.findLocationById(id);
        return location.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/trips/search")
    public ResponseEntity<List<TripFullDTO>> searchTrips(@RequestBody SearchTripDTO searchCriteria) {
        List<TripFullDTO> trips = catalogService.searchTrips(searchCriteria);
        return ResponseEntity.ok(trips);
    }

    @GetMapping("/locations/search")
    public ResponseEntity<List<LocationDTO>> searchLocations(@RequestBody SearchLocationDTO searchCriteria) {
        List<LocationDTO> locations = catalogService.searchLocations(searchCriteria);
        return ResponseEntity.ok(locations);
    }
}
