package com.frro.bus.ticket.features.journey.specifications;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.frro.bus.ticket.features.booking.entities.Ticket;
import com.frro.bus.ticket.features.fleet.entities.Seat;
import com.frro.bus.ticket.features.journey.dtos.trip.SearchTripDTO;
import com.frro.bus.ticket.features.journey.entities.Trip;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

public final class TripSpecification {

    private TripSpecification() {
    }

    public static Specification<Trip> build(SearchTripDTO criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            criteria.startDepartureDate().ifPresent(value ->
                    predicates.add(cb.greaterThanOrEqualTo(root.get("departureDate"), value)));
            criteria.endDepartureDate().ifPresent(value ->
                    predicates.add(cb.lessThanOrEqualTo(root.get("departureDate"), value)));
            criteria.startArrivalDate().ifPresent(value ->
                    predicates.add(cb.greaterThanOrEqualTo(root.get("arrivalDate"), value)));
            criteria.endArrivalDate().ifPresent(value ->
                    predicates.add(cb.lessThanOrEqualTo(root.get("arrivalDate"), value)));
            criteria.startBasePrice().ifPresent(value ->
                    predicates.add(cb.greaterThanOrEqualTo(root.get("basePrice"), value)));
            criteria.endBasePrice().ifPresent(value ->
                    predicates.add(cb.lessThanOrEqualTo(root.get("basePrice"), value)));
            criteria.busId().ifPresent(value ->
                    predicates.add(cb.equal(root.get("bus").get("id"), value)));
            criteria.driverId().ifPresent(value ->
                    predicates.add(cb.equal(root.get("driver").get("id"), value)));
            criteria.locationOriginId().ifPresent(value ->
                    predicates.add(cb.equal(root.get("locationOrigin").get("id"), value)));
            criteria.locationDestinationId().ifPresent(value ->
                    predicates.add(cb.equal(root.get("locationDestination").get("id"), value)));
            criteria.seatTypeId().ifPresent(value ->
                    predicates.add(hasSeatType(root, query, cb, value)));

            return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Trip> available(ZonedDateTime timeBuffer) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.greaterThan(root.get("departureDate"), timeBuffer));
            predicates.add(hasFreeSeats(root, query, cb));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Trip> conflictingDriver(int driverId, int excludeTripId,
            ZonedDateTime departureDateMinusBuffer, ZonedDateTime arrivalDatePlusBuffer) {
        return (root, query, cb) -> cb.and(
                cb.equal(root.get("driver").get("id"), driverId),
                cb.notEqual(root.get("id"), excludeTripId),
                cb.lessThan(root.get("departureDate"), arrivalDatePlusBuffer),
                cb.greaterThan(root.get("arrivalDate"), departureDateMinusBuffer));
    }

    public static Specification<Trip> conflictingBus(int busId, int excludeTripId,
            ZonedDateTime departureDateMinusBuffer, ZonedDateTime arrivalDatePlusBuffer) {
        return (root, query, cb) -> cb.and(
                cb.equal(root.get("bus").get("id"), busId),
                cb.notEqual(root.get("id"), excludeTripId),
                cb.lessThan(root.get("departureDate"), arrivalDatePlusBuffer),
                cb.greaterThan(root.get("arrivalDate"), departureDateMinusBuffer));
    }

    private static Predicate hasSeatType(Root<Trip> root, CriteriaQuery<?> query, CriteriaBuilder cb, int seatTypeId) {
        Subquery<Long> sub = query.subquery(Long.class);
        Root<Seat> seat = sub.from(Seat.class);
        sub.select(cb.count(seat))
                .where(
                        cb.equal(seat.get("bus"), root.get("bus")),
                        cb.equal(seat.get("seatType").get("id"), seatTypeId));
        return cb.greaterThan(sub, 0L);
    }

    private static Predicate hasFreeSeats(Root<Trip> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Subquery<Long> booked = query.subquery(Long.class);
        Root<Ticket> ticket = booked.from(Ticket.class);
        booked.select(cb.count(ticket))
                .where(
                        cb.equal(ticket.get("trip"), root),
                        cb.isFalse(ticket.get("isCancelled")));

        Subquery<Long> activeSeats = query.subquery(Long.class);
        Root<Seat> seat = activeSeats.from(Seat.class);
        activeSeats.select(cb.count(seat))
                .where(
                        cb.equal(seat.get("bus"), root.get("bus")),
                        cb.isTrue(seat.get("isActive")));

        return cb.lessThan(booked, activeSeats);
    }
}
