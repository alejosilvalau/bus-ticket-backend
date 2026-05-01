package com.frro.bus.ticket.features.journey.services.catalog;

import java.util.List;
import java.util.Optional;

import com.frro.bus.ticket.features.journey.dtos.trip.SearchTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.SearchLocationDTO;

public interface CatalogService {
    List<TripDTO> findAllTrips();

    List<LocationDTO> findAllLocations();

    Optional<TripDTO> findTripById(int id);

    Optional<LocationDTO> findLocationById(int id);

    List<TripDTO> searchTrips(SearchTripDTO searchCriteria);

    List<LocationDTO> searchLocations(SearchLocationDTO searchCriteria);
}
