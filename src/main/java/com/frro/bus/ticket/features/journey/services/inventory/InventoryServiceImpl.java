package com.frro.bus.ticket.features.journey.services.inventory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frro.bus.ticket.common.exceptions.ResourceNotFoundException;
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
    @Transactional
    public TripFullDTO updateTrip(UpdateTripDTO tripRequest) {
        Trip existingTrip = tripRepository.findById(tripRequest.id())
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", tripRequest.id()));

        tripRequest.departureDate().ifPresent(existingTrip::setDepartureDate);
        tripRequest.arrivalDate().ifPresent(existingTrip::setArrivalDate);
        tripRequest.basePrice().ifPresent(existingTrip::setBasePrice);

        tripRequest.idBus().ifPresent(idBus -> {
            Bus bus = new Bus();
            bus.setId(idBus);
            existingTrip.setBus(bus);
        });
        tripRequest.idDriver().ifPresent(idDriver -> {
            Driver driver = new Driver();
            driver.setId(idDriver);
            existingTrip.setDriver(driver);
        });
        tripRequest.idLocationOrigin().ifPresent(idLocation -> {
            Location location = new Location();
            location.setId(idLocation);
            existingTrip.setLocationOrigin(location);
        });
        tripRequest.idLocationDestination().ifPresent(idLocation -> {
            Location location = new Location();
            location.setId(idLocation);
            existingTrip.setLocationDestination(location);
        });

        Trip savedTrip = tripRepository.save(existingTrip);
        entityManager.flush();
        entityManager.refresh(savedTrip);
        return tripMapper.toTripFullDTO(savedTrip);
    }

    @Override
    public TripFullDTO deleteTrip(int id) {
        Trip existingTrip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", id));
        tripRepository.delete(existingTrip);
        return tripMapper.toTripFullDTO(existingTrip);
    }

    @Override
    public LocationDTO createLocation(CreateLocationDTO locationRequest) {
        Location location = locationMapper.toLocation(locationRequest);
        Location saved = locationRepository.save(location);
        return locationMapper.toLocationDTO(saved);
    }

    @Override
    public LocationDTO updateLocation(UpdateLocationDTO locationRequest) {
        Location existingLocation = locationRepository.findById(locationRequest.id())
                .orElseThrow(() -> new ResourceNotFoundException("Location", "id", locationRequest.id()));

        locationRequest.cityName().ifPresent(existingLocation::setCityName);
        locationRequest.state().ifPresent(existingLocation::setState);
        locationRequest.postalCode().ifPresent(existingLocation::setPostalCode);

        Location savedLocation = locationRepository.save(existingLocation);
        return locationMapper.toLocationDTO(savedLocation);
    }

    @Override
    public LocationDTO deleteLocation(int id) {
        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location", "id", id));
        locationRepository.delete(existingLocation);
        return locationMapper.toLocationDTO(existingLocation);
    }
}
