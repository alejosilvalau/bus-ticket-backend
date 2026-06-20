package com.frro.bus.ticket.features.journey.services.inventory;

import java.time.Duration;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

import com.frro.bus.ticket.common.exceptions.BusinessException;
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

    @Override
    public TripFullDTO createTrip(CreateTripDTO tripRequest) {
        Trip trip = tripMapper.toTrip(tripRequest);

        trip.setBus(validateBusRelationship(tripRequest.busId()));
        trip.setDriver(validateDriverRelationship(tripRequest.driverId()));
        trip.setLocationOrigin(validateLocationRelationship(tripRequest.locationOriginId()));
        trip.setLocationDestination(validateLocationRelationship(tripRequest.locationDestinationId()));

        validateTripDates(trip.getDepartureDate(), trip.getArrivalDate());

        validateTripLocations(trip.getLocationOrigin().getId(), trip.getLocationDestination().getId());

        int excludeTripId = 0; // 0 means we are creating a new trip, so no existing trip to exclude
        validateDriverAvailability(tripRequest.driverId(), excludeTripId, tripRequest.departureDate(),
                tripRequest.arrivalDate());
        validateBusAvailability(tripRequest.busId(), excludeTripId, tripRequest.departureDate(),
                tripRequest.arrivalDate());

        Trip saved = tripRepository.save(trip);
        return tripMapper.toTripFullDTO(saved);
    }

    @Override
    public TripFullDTO updateTrip(UpdateTripDTO tripRequest) {
        Trip existingTrip = tripRepository.findById(tripRequest.id())
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", tripRequest.id()));

        tripRequest.busId().ifPresent(busId -> {
            existingTrip.setBus(validateBusRelationship(busId));
        });
        tripRequest.driverId().ifPresent(driverId -> {
            existingTrip.setDriver(validateDriverRelationship(driverId));
        });
        tripRequest.locationOriginId().ifPresent(locationOriginId -> {
            existingTrip.setLocationOrigin(validateLocationRelationship(locationOriginId));
        });
        tripRequest.locationDestinationId().ifPresent(locationDestinationId -> {
            existingTrip.setLocationDestination(validateLocationRelationship(locationDestinationId));
        });

        tripRequest.departureDate().ifPresent(existingTrip::setDepartureDate);
        tripRequest.arrivalDate().ifPresent(existingTrip::setArrivalDate);
        tripRequest.basePrice().ifPresent(existingTrip::setBasePrice);

        validateTripDates(existingTrip.getDepartureDate(), existingTrip.getArrivalDate());

        validateTripLocations(existingTrip.getLocationOrigin().getId(), existingTrip.getLocationDestination().getId());

        validateDriverAvailability(existingTrip.getDriver().getId(), existingTrip.getId(),
                existingTrip.getDepartureDate(), existingTrip.getArrivalDate());
        validateBusAvailability(existingTrip.getBus().getId(), existingTrip.getId(), existingTrip.getDepartureDate(),
                existingTrip.getArrivalDate());

        Trip savedTrip = tripRepository.save(existingTrip);
        return tripMapper.toTripFullDTO(savedTrip);
    }

    private Bus validateBusRelationship(int busId) {
        return busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus", "id", busId));
    }

    private Driver validateDriverRelationship(int driverId) {
        return driverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver", "id", driverId));
    }

    private Location validateLocationRelationship(int locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location", "id", locationId));
    }

    private void validateTripDates(ZonedDateTime departureDate, ZonedDateTime arrivalDate) {
        if (departureDate.isBefore(ZonedDateTime.now(ZoneOffset.UTC))) {
            throw new BusinessException("Departure date must be in the future.");
        }
        if (arrivalDate.isBefore(departureDate) || arrivalDate.isEqual(departureDate)) {
            throw new BusinessException("Arrival date must be after departure date.");
        }
    }

    private void validateTripLocations(int locationOriginId, int locationDestinationId) {
        if (locationOriginId == locationDestinationId) {
            throw new BusinessException("Origin and destination locations must be different.");
        }
    }

    private void validateDriverAvailability(int driverId, int excludeTripId, ZonedDateTime departureDate,
            ZonedDateTime arrivalDate) {
        long hoursBetweenDriverTrips = 3;
        ZonedDateTime departureMinusBuffer = departureDate.minus(Duration.ofHours(hoursBetweenDriverTrips));
        ZonedDateTime arrivalPlusBuffer = arrivalDate.plus(Duration.ofHours(hoursBetweenDriverTrips));
        if (tripRepository.existsConflictingDriverTrip(driverId, excludeTripId, departureMinusBuffer,
                arrivalPlusBuffer)) {
            throw new BusinessException(String.format("Driver is not available: has another trip within %d hours.",
                    hoursBetweenDriverTrips));
        }
    }

    private void validateBusAvailability(int busId, int excludeTripId, ZonedDateTime departureDate,
            ZonedDateTime arrivalDate) {
        long minutesBetweenBusTrips = 30;
        ZonedDateTime departureMinusBuffer = departureDate.minus(Duration.ofMinutes(minutesBetweenBusTrips));
        ZonedDateTime arrivalPlusBuffer = arrivalDate.plus(Duration.ofMinutes(minutesBetweenBusTrips));
        if (tripRepository.existsConflictingBusTrip(busId, excludeTripId, departureMinusBuffer, arrivalPlusBuffer)) {
            throw new BusinessException(String.format("Bus is not available: has another trip within %d minutes.",
                    minutesBetweenBusTrips));
        }
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
        int excludeId = 0; // 0 means we are creating a new location, so no existing location to exclude
        validateLocationUniqueness(locationRequest.cityName(), locationRequest.state(), locationRequest.postalCode(),
                excludeId);

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

        validateLocationUniqueness(existingLocation.getCityName(), existingLocation.getState(),
                existingLocation.getPostalCode(), existingLocation.getId());

        Location savedLocation = locationRepository.save(existingLocation);
        return locationMapper.toLocationDTO(savedLocation);
    }

    private void validateLocationUniqueness(String cityName, String state, String postalCode, int excludeId) {
        locationRepository.findByCityNameAndStateAndPostalCode(cityName, state, postalCode)
                .ifPresent(location -> {
                    if (location.getId() != excludeId) {
                        throw new DuplicateResourceException("Location", "city+state+postalCode",
                                cityName + ", " + state + ", " + postalCode);
                    }
                });
    }

    @Override
    public LocationDTO deleteLocation(int id) {
        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location", "id", id));
        locationRepository.delete(existingLocation);
        return locationMapper.toLocationDTO(existingLocation);
    }
}
