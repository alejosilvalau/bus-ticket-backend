package com.frro.bus.ticket.features.journey.services.catalog;

import java.util.List;
import java.util.Optional;

import com.frro.bus.ticket.features.journey.dtos.trip.SearchTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripFullDTO;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.SearchLocationDTO;

public interface CatalogService {
    List<TripFullDTO> findAllTrips();

    List<TripFullDTO> searchTrips(SearchTripDTO searchCriteria);

    Optional<TripFullDTO> findTripById(int id);

    List<LocationDTO> findAllLocations();

    List<LocationDTO> searchLocations(SearchLocationDTO searchCriteria);

    Optional<LocationDTO> findLocationById(int id);
}
