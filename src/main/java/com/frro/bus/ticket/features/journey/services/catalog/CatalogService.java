package com.frro.bus.ticket.features.journey.services.catalog;

import java.util.List;
import java.util.Optional;

import com.frro.bus.ticket.features.journey.dtos.trip.SearchTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripFullDTO;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.SearchLocationDTO;

public interface CatalogService {
    List<TripFullDTO> findAllTrips();

    List<LocationDTO> findAllLocations();

    Optional<TripFullDTO> findTripById(int id);

    Optional<LocationDTO> findLocationById(int id);

    List<TripFullDTO> searchTrips(SearchTripDTO searchCriteria);

    // List<LocationDTO> searchLocations(SearchLocationDTO searchCriteria);
}
