package com.frro.bus.ticket.features.journey.services.catalog;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.frro.bus.ticket.common.exceptions.ResourceNotFoundException;
import com.frro.bus.ticket.features.journey.dtos.trip.SearchTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripFullDTO;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.SearchLocationDTO;
import com.frro.bus.ticket.features.journey.mappers.TripMapper;
import com.frro.bus.ticket.features.journey.mappers.LocationMapper;
import com.frro.bus.ticket.features.journey.repositories.TripRepository;
import com.frro.bus.ticket.features.journey.repositories.LocationRepository;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private final TripRepository tripRepository;
    private final LocationRepository locationRepository;
    private final TripMapper tripMapper;
    private final LocationMapper locationMapper;

    @Override
    public Page<TripFullDTO> findAllTrips(Pageable pageable) {
        return tripRepository.findAll(pageable).map(tripMapper::toTripFullDTO);
    }

    @Override
    public Page<TripFullDTO> searchTrips(SearchTripDTO searchCriteria, Pageable pageable) {
        return tripRepository.searchTrips(
                searchCriteria.departureDate().orElse(null),
                searchCriteria.arrivalDate().orElse(null),
                searchCriteria.startBasePrice().orElse(null),
                searchCriteria.endBasePrice().orElse(null),
                searchCriteria.busId().orElse(null),
                searchCriteria.driverId().orElse(null),
                searchCriteria.locationOriginId().orElse(null),
                searchCriteria.locationDestinationId().orElse(null),
                pageable)
                .map(tripMapper::toTripFullDTO);
    }

    @Override
    public TripFullDTO findTripById(int id) {
        return tripRepository.findById(id)
                .map(tripMapper::toTripFullDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", id));
    }

    @Override
    public Page<LocationDTO> findAllLocations(Pageable pageable) {
        return locationRepository.findAll(pageable).map(locationMapper::toLocationDTO);
    }

    @Override
    public Page<LocationDTO> searchLocations(SearchLocationDTO searchCriteria, Pageable pageable) {
        return locationRepository.searchLocations(
                searchCriteria.cityName().orElse(null),
                searchCriteria.state().orElse(null),
                searchCriteria.postalCode().orElse(null),
                pageable)
                .map(locationMapper::toLocationDTO);
    }

    @Override
    public LocationDTO findLocationById(int id) {
        return locationRepository.findById(id)
                .map(locationMapper::toLocationDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Location", "id", id));
    }
}
