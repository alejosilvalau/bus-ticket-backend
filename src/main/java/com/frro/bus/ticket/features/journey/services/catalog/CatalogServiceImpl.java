package com.frro.bus.ticket.features.journey.services.catalog;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.frro.bus.ticket.features.journey.dtos.trip.SearchTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripFullDTO;
import com.frro.bus.ticket.features.journey.entities.Location;
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

    @Override
    public List<TripFullDTO> findAllTrips() {
        return tripRepository.findAll().stream().map(tripMapper::toTripFullDTO).toList();
    }

    @Override
    public List<LocationDTO> findAllLocations() {
        return locationRepository.findAll().stream().map(locationMapper::toLocationDTO).toList();
    }

    @Override
    public Optional<TripFullDTO> findTripById(int id) {
        return tripRepository.findById(id).map(tripMapper::toTripFullDTO);
    }

    @Override
    public Optional<LocationDTO> findLocationById(int id) {
        return locationRepository.findById(id).map(locationMapper::toLocationDTO);
    }

    @Override
    public List<TripFullDTO> searchTrips(SearchTripDTO searchCriteria) {
        return tripRepository.searchTrips(
                searchCriteria.departureDate().orElse(null),
                searchCriteria.arrivalDate().orElse(null),
                searchCriteria.startBasePrice().orElse(null),
                searchCriteria.endBasePrice().orElse(null),
                searchCriteria.idBus().orElse(null),
                searchCriteria.idDriver().orElse(null),
                searchCriteria.idLocationOrigin().orElse(null),
                searchCriteria.idLocationDestination().orElse(null))
                .stream()
                .map(tripMapper::toTripFullDTO)
                .toList();
    }

    // @Override
    // public List<LocationDTO> searchLocations(SearchLocationDTO searchCriteria) {
    // Location probe = locationMapper.toLocation(searchCriteria);
    //
    // return locationRepository.findAll(Example.of(probe,
    // SearchServiceUtils.DEFAULT_MATCHER))
    // .stream()
    // .map(locationMapper::toLocationDTO)
    // .toList();
    // }
}
