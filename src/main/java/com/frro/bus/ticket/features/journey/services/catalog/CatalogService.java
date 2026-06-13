package com.frro.bus.ticket.features.journey.services.catalog;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.frro.bus.ticket.features.journey.dtos.trip.SearchTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripFullDTO;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.SearchLocationDTO;

public interface CatalogService {
    Page<TripFullDTO> findAllTrips(Pageable pageable);

    Page<TripFullDTO> searchTrips(SearchTripDTO searchCriteria, Pageable pageable);

    Optional<TripFullDTO> findTripById(int id);

    Page<LocationDTO> findAllLocations(Pageable pageable);

    Page<LocationDTO> searchLocations(SearchLocationDTO searchCriteria, Pageable pageable);

    Optional<LocationDTO> findLocationById(int id);
}
