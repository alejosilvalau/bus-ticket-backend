package com.frro.bus.ticket.features.journey.services.inventory;

import com.frro.bus.ticket.features.journey.dtos.location.CreateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.UpdateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.CreateTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripFullDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.UpdateTripDTO;

public interface InventoryService {
    TripFullDTO createTrip(CreateTripDTO userRequest);

    TripFullDTO updateTrip(UpdateTripDTO userRequest);

    TripFullDTO deleteTrip(int id);

    LocationDTO createLocation(CreateLocationDTO userRequest);

    LocationDTO updateLocation(UpdateLocationDTO userRequest);

    LocationDTO deleteLocation(int id);
}
