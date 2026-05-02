package com.frro.bus.ticket.features.journey.services.catalog;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.frro.bus.ticket.features.journey.dtos.trip.SearchTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;
import com.frro.bus.ticket.features.journey.entities.Location;
import com.frro.bus.ticket.features.journey.entities.Trip;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.SearchLocationDTO;
import com.frro.bus.ticket.features.journey.mappers.TripMapper;
import com.frro.bus.ticket.features.journey.mappers.LocationMapper;
import com.frro.bus.ticket.features.journey.repositories.TripRepository;
import com.frro.bus.ticket.features.journey.repositories.LocationRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private final TripRepository tripRepository;
    private final LocationRepository locationRepository;
    private final TripMapper tripMapper;
    private final LocationMapper locationMapper;

    private static final ExampleMatcher DEFAULT_MATCHER = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

    @Override
    public List<TripDTO> findAllTrips() {
        return tripRepository.findAll().stream().map(tripMapper::toTripDTO).toList();
    }

    @Override
    public List<LocationDTO> findAllLocations() {
        return locationRepository.findAll().stream().map(locationMapper::toLocationDTO).toList();
    }

    @Override
    public Optional<TripDTO> findTripById(int id) {
        return tripRepository.findById(id).map(tripMapper::toTripDTO);
    }

    @Override
    public Optional<LocationDTO> findLocationById(int id) {
        return locationRepository.findById(id).map(locationMapper::toLocationDTO);
    }

    @Override
    public List<TripDTO> searchTrips(SearchTripDTO searchCriteria) {
        Trip probe = tripMapper.toTrip(searchCriteria);

        return tripRepository.findAll(Example.of(probe, DEFAULT_MATCHER))
                .stream()
                .map(tripMapper::toTripDTO)
                .toList();
    }

    @Override
    public List<LocationDTO> searchLocations(SearchLocationDTO searchCriteria) {
        Location probe = locationMapper.toLocation(searchCriteria);

        return locationRepository.findAll(Example.of(probe, DEFAULT_MATCHER))
                .stream()
                .map(locationMapper::toLocationDTO)
                .toList();
    }
}
