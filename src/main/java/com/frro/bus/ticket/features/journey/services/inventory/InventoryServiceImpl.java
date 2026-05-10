package com.frro.bus.ticket.features.journey.services.inventory;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frro.bus.ticket.features.fleet.entities.Bus;
import com.frro.bus.ticket.features.identity.entities.Driver;
import com.frro.bus.ticket.features.journey.dtos.location.CreateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.LocationDTO;
import com.frro.bus.ticket.features.journey.dtos.location.UpdateLocationDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.CreateTripDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripFullDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.UpdateTripDTO;
import com.frro.bus.ticket.features.journey.entities.Location;
import com.frro.bus.ticket.features.journey.entities.Trip;
import com.frro.bus.ticket.features.journey.mappers.LocationMapper;
import com.frro.bus.ticket.features.journey.mappers.TripMapper;
import com.frro.bus.ticket.features.journey.repositories.LocationRepository;
import com.frro.bus.ticket.features.journey.repositories.TripRepository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final TripRepository tripRepository;
    private final LocationRepository locationRepository;
    private final TripMapper tripMapper;
    private final LocationMapper locationMapper;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public TripFullDTO createTrip(CreateTripDTO tripRequest) {
        Trip trip = tripMapper.toTrip(tripRequest);
        Trip saved = tripRepository.save(trip);
        entityManager.flush();
        entityManager.refresh(saved);
        return tripMapper.toTripFullDTO(saved);
    }

    @Override
    public LocationDTO createLocation(CreateLocationDTO locationRequest) {
        Location location = locationMapper.toLocation(locationRequest);
        Location saved = locationRepository.save(location);
        return locationMapper.toLocationDTO(saved);
    }

    @Override
    @Transactional
    public Optional<TripFullDTO> updateTrip(UpdateTripDTO tripRequest) {
        return tripRepository.findById(tripRequest.id()).map(existingTrip -> {
            tripRequest.departureDate().ifPresent(existingTrip::setDepartureDate);
            tripRequest.arrivalDate().ifPresent(existingTrip::setArrivalDate);
            tripRequest.basePrice().ifPresent(existingTrip::setBasePrice);

            // Update relationships if provided
            tripRequest.idBus().ifPresent(busId -> {
                Bus bus = new Bus();
                bus.setId(busId);
                existingTrip.setBus(bus);
            });
            tripRequest.idDriver().ifPresent(driverId -> {
                Driver driver = new Driver();
                driver.setId(driverId);
                existingTrip.setDriver(driver);
            });
            tripRequest.idLocationOrigin().ifPresent(locationId -> {
                Location location = new Location();
                location.setId(locationId);
                existingTrip.setLocationOrigin(location);
            });
            tripRequest.idLocationDestination().ifPresent(locationId -> {
                Location location = new Location();
                location.setId(locationId);
                existingTrip.setLocationDestination(location);
            });

            Trip savedTrip = tripRepository.save(existingTrip);
            entityManager.flush();
            entityManager.refresh(savedTrip);
            return tripMapper.toTripFullDTO(savedTrip);
        });
    }

    @Override
    public Optional<LocationDTO> updateLocation(UpdateLocationDTO locationRequest) {
        return locationRepository.findById(locationRequest.id()).map(existingLocation -> {
            locationRequest.cityName().ifPresent(existingLocation::setCityName);
            locationRequest.state().ifPresent(existingLocation::setState);
            locationRequest.postalCode().ifPresent(existingLocation::setPostalCode);

            Location savedLocation = locationRepository.save(existingLocation);
            return locationMapper.toLocationDTO(savedLocation);
        });
    }

    @Override
    public Optional<TripFullDTO> deleteTrip(int id) {
        return tripRepository.findById(id).map(existingTrip -> {
            tripRepository.delete(existingTrip);
            return tripMapper.toTripFullDTO(existingTrip);
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
