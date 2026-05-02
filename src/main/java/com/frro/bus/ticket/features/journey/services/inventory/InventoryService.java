package com.frro.bus.ticket.features.journey.services.inventory;

import java.util.Optional;

import com.frro.bus.ticket.features.journey.dtos.location.CreateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.UpdateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.CreateTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.UpdateTripDTO;

public interface InventoryService {
    TripDTO createTrip(CreateTripDTO userRequest);

    LocationDTO createLocation(CreateLocationDTO userRequest);

    Optional<TripDTO> updateTrip(UpdateTripDTO userRequest);

    Optional<LocationDTO> updateLocation(UpdateLocationDTO userRequest);

    Optional<TripDTO> deleteTrip(int id);

    Optional<LocationDTO> deleteLocation(int id);
}
