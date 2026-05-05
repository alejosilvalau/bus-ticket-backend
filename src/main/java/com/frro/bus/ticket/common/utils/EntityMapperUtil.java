package com.frro.bus.ticket.common.utils;

import java.util.Optional;
import java.util.function.Supplier;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import com.frro.bus.ticket.features.booking.entities.Ticket;
import com.frro.bus.ticket.features.fleet.entities.Bus;
import com.frro.bus.ticket.features.fleet.entities.Seat;
import com.frro.bus.ticket.features.fleet.entities.SeatType;
import com.frro.bus.ticket.features.identity.entities.Driver;
import com.frro.bus.ticket.features.identity.entities.User;
import com.frro.bus.ticket.features.journey.entities.Location;
import com.frro.bus.ticket.features.journey.entities.Trip;

@Mapper(componentModel = "spring")
public class EntityMapperUtil {

    // Entity to id mappers

    @Named("busToId")
    public Integer busToId(Bus bus) {
        return entityToId(bus);
    }

    @Named("driverToId")
    public Integer driverToId(Driver driver) {
        return entityToId(driver);
    }

    @Named("locationToId")
    public Integer locationToId(Location location) {
        return entityToId(location);
    }

    @Named("userToId")
    public Integer userToId(User user) {
        return entityToId(user);
    }

    @Named("tripToId")
    public Integer tripToId(Trip trip) {
        return entityToId(trip);
    }

    @Named("seatToId")
    public Integer seatToId(Seat seat) {
        return entityToId(seat);
    }

    @Named("seatTypeToId")
    public Integer seatTypeToId(SeatType seatType) {
        return entityToId(seatType);
    }

    @Named("ticketToId")
    public Integer ticketToId(Ticket ticket) {
        return entityToId(ticket);
    }

    // Id to entity mappers

    @Named("idToBus")
    public Bus idToBus(Integer busId) {
        return idToEntity(busId, Bus::new);
    }

    @Named("idToDriver")
    public Driver idToDriver(Integer driverId) {
        return idToEntity(driverId, Driver::new);
    }

    @Named("idToLocation")
    public Location idToLocation(Integer locationId) {
        return idToEntity(locationId, Location::new);
    }

    @Named("idToUser")
    public User idToUser(Integer userId) {
        return idToEntity(userId, User::new);
    }

    @Named("idToTrip")
    public Trip idToTrip(Integer tripId) {
        return idToEntity(tripId, Trip::new);
    }

    @Named("idToSeat")
    public Seat idToSeat(Integer seatId) {
        return idToEntity(seatId, Seat::new);
    }

    @Named("idToSeatType")
    public SeatType idToSeatType(Integer seatTypeId) {
        return idToEntity(seatTypeId, SeatType::new);
    }

    @Named("idToTicket")
    public Ticket idToTicket(Integer ticketId) {
        return idToEntity(ticketId, Ticket::new);
    }

    // Optional Id to entity mappers

    @Named("optionalIdToBus")
    public Bus idToBusOptional(Optional<Integer> idBus) {
        return optionalIdToEntity(idBus, Bus::new);
    }

    @Named("optionalIdToDriver")
    public Driver idToDriverOptional(Optional<Integer> idDriver) {
        return optionalIdToEntity(idDriver, Driver::new);
    }

    @Named("optionalIdToLocation")
    public Location idToLocationOptional(Optional<Integer> idLocation) {
        return optionalIdToEntity(idLocation, Location::new);
    }

    @Named("optionalIdToUser")
    public User idToUserOptional(Optional<Integer> idUser) {
        return optionalIdToEntity(idUser, User::new);
    }

    @Named("optionalIdToTrip")
    public Trip idToTripOptional(Optional<Integer> idTrip) {
        return optionalIdToEntity(idTrip, Trip::new);
    }

    @Named("optionalIdToSeat")
    public Seat idToSeatOptional(Optional<Integer> idSeat) {
        return optionalIdToEntity(idSeat, Seat::new);
    }

    @Named("optionalIdToSeatType")
    public SeatType idToSeatTypeOptional(Optional<Integer> idSeatType) {
        return optionalIdToEntity(idSeatType, SeatType::new);
    }

    @Named("optionalIdToTicket")
    public Ticket idToTicketOptional(Optional<Integer> idTicket) {
        return optionalIdToEntity(idTicket, Ticket::new);
    }

    // Generic helpers

    protected <T extends EntityWithId> Integer entityToId(T entity) {
        return entity != null ? entity.getId() : null;
    }

    protected <T extends EntityWithId> T idToEntity(Integer id, Supplier<T> constructor) {
        if (id == null)
            return null;
        T entity = constructor.get();
        entity.setId(id);
        return entity;
    }

    protected <T extends EntityWithId> T optionalIdToEntity(Optional<Integer> id, Supplier<T> constructor) {
        return id.map(idVal -> idToEntity(idVal, constructor)).orElse(null);
    }
}
