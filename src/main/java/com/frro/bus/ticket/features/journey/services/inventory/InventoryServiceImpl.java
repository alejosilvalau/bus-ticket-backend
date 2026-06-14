package com.frro.bus.ticket.features.journey.services.inventory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frro.bus.ticket.common.exceptions.DuplicateResourceException;
import com.frro.bus.ticket.common.exceptions.ResourceNotFoundException;
import com.frro.bus.ticket.features.fleet.entities.Bus;
import com.frro.bus.ticket.features.fleet.repositories.BusRepository;
import com.frro.bus.ticket.features.identity.entities.Driver;
import com.frro.bus.ticket.features.identity.repositories.DriverRepository;
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
    private final BusRepository busRepository;
    private final DriverRepository driverRepository;
    private final TripMapper tripMapper;
    private final LocationMapper locationMapper;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public TripFullDTO createTrip(CreateTripDTO tripRequest) {
        tripRepository.findByBusIdAndDepartureDate(tripRequest.busId(), tripRequest.departureDate())
                .ifPresent(trip -> {
                    throw new DuplicateResourceException("Trip", "bus+departureDate",
                            "bus=" + tripRequest.busId() + ", departure=" + tripRequest.departureDate());
                });

        tripRepository.findByDriverIdAndDepartureDate(tripRequest.driverId(), tripRequest.departureDate())
                .ifPresent(trip -> {
                    throw new DuplicateResourceException("Trip", "driver+departureDate",
                            "driver=" + tripRequest.driverId() + ", departure=" + tripRequest.departureDate());
                });

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

        tripRequest.busId().ifPresent(busId -> {
            Bus bus = busRepository.findById(busId)
                    .orElseThrow(() -> new ResourceNotFoundException("Bus", "id", busId));
            existingTrip.setBus(bus);
        });
        tripRequest.driverId().ifPresent(driverId -> {
            Driver driver = driverRepository.findById(driverId)
                    .orElseThrow(() -> new ResourceNotFoundException("Driver", "id", driverId));
            existingTrip.setDriver(driver);
        });
        tripRequest.locationOriginId().ifPresent(locationOriginId -> {
            Location location = locationRepository.findById(locationOriginId)
                    .orElseThrow(() -> new ResourceNotFoundException("Location", "id", locationOriginId));
            existingTrip.setLocationOrigin(location);
        });
        tripRequest.locationDestinationId().ifPresent(locationDestinationId -> {
            Location location = locationRepository.findById(locationDestinationId)
                    .orElseThrow(() -> new ResourceNotFoundException("Location", "id", locationDestinationId));
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
        locationRepository.findByCityNameAndStateAndPostalCode(
                locationRequest.cityName(), locationRequest.state(), locationRequest.postalCode())
                .ifPresent(location -> {
                    throw new DuplicateResourceException("Location", "city+state+postalCode",
                            locationRequest.cityName() + ", " + locationRequest.state() + ", " + locationRequest.postalCode());
                });

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
