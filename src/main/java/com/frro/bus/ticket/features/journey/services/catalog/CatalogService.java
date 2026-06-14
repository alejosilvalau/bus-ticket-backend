package com.frro.bus.ticket.features.journey.services.catalog;

import org.springframework.data.domain.Pageable;

import com.frro.bus.ticket.common.dto.PageResponse;
import com.frro.bus.ticket.features.journey.dtos.trip.SearchTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripFullDTO;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.SearchLocationDTO;

public interface CatalogService {
    PageResponse<TripFullDTO> findAllTrips(Pageable pageable);

    PageResponse<TripFullDTO> searchTrips(SearchTripDTO searchCriteria, Pageable pageable);

    TripFullDTO findTripById(int id);

    PageResponse<LocationDTO> findAllLocations(Pageable pageable);

    PageResponse<LocationDTO> searchLocations(SearchLocationDTO searchCriteria, Pageable pageable);

    LocationDTO findLocationById(int id);
}
