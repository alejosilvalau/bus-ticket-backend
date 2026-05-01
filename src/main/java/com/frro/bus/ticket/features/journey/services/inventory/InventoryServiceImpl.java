package com.frro.bus.ticket.features.journey.services.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.frro.bus.ticket.features.journey.dtos.location.CreateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.UpdateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.CreateTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.UpdateTripDTO;
import com.frro.bus.ticket.features.journey.entities.Trip;
import com.frro.bus.ticket.features.journey.entities.Location;
import com.frro.bus.ticket.features.journey.mappers.TripMapper;
import com.frro.bus.ticket.features.journey.mappers.LocationMapper;
import com.frro.bus.ticket.features.journey.repositories.TripRepository;
import com.frro.bus.ticket.features.journey.repositories.LocationRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final TripRepository tripRepository;
    private final LocationRepository locationRepository;

    @Override
    public TripDTO createTrip(CreateTripDTO tripRequest) {
        Trip trip = tripMapper.toTrip(tripRequest);
        Trip saved = tripRepository.save(trip);
        return tripMapper.toTripDTO(saved);
    }

    @Override
    public LocationDTO createLocation(CreateLocationDTO locationRequest) {
        Location location = locationMapper.toLocation(locationRequest);
        Location saved = locationRepository.save(location);
        return locationMapper.toLocationDTO(saved);
    }

    @Override
    public Optional<TripDTO> updateTrip(int id, UpdateTripDTO tripRequest) {
        return tripRepository.findById(id).map(existingTrip -> {
            existingTrip.setDepartureDate(tripRequest.departureDate());
            existingTrip.setArrivalDate(tripRequest.arrivalDate());
            existingTrip.setBasePrice(tripRequest.basePrice());

            Trip savedTrip = tripRepository.save(existingTrip);
            return tripMapper.toTripDTO(savedTrip);
        });
    }

    @Override
    public Optional<LocationDTO> updateLocation(int id, UpdateLocationDTO locationRequest) {
        return locationRepository.findById(id).map(existingLocation -> {
            existingLocation.setCityName(locationRequest.cityName());
            existingLocation.setState(locationRequest.state());

            Location savedLocation = locationRepository.save(existingLocation);
            return locationMapper.toLocationDTO(savedLocation);
        });
    }

    @Override
    public Optional<TripDTO> deleteTrip(int id) {
        return tripRepository.findById(id).map(existingTrip -> {
            tripRepository.delete(existingTrip);
            return tripMapper.toTripDTO(existingTrip);
        });
    }

    @Override
    public Optional<LocationDTO> deleteLocation(int id) {
        return locationRepository.findById(id).map(existingLocation -> {
            locationRepository.delete(existingLocation);
            return locationMapper.toLocationDTO(existingLocation);
        });
    }
}
