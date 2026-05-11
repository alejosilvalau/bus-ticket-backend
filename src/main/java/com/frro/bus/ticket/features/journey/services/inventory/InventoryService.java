package com.frro.bus.ticket.features.journey.services.inventory;

import java.util.Optional;

import com.frro.bus.ticket.features.journey.dtos.location.CreateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.UpdateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.CreateTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripFullDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.UpdateTripDTO;

public interface InventoryService {
    TripFullDTO createTrip(CreateTripDTO userRequest);

    Optional<TripFullDTO> updateTrip(UpdateTripDTO userRequest);

    Optional<TripFullDTO> deleteTrip(int id);

    LocationDTO createLocation(CreateLocationDTO userRequest);

    Optional<LocationDTO> updateLocation(UpdateLocationDTO userRequest);

    Optional<LocationDTO> deleteLocation(int id);
}
