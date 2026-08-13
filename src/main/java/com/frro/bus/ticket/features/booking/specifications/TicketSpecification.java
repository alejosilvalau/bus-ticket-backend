package com.frro.bus.ticket.features.booking.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.frro.bus.ticket.features.booking.dtos.SearchTicketDTO;
import com.frro.bus.ticket.features.booking.entities.Ticket;

import jakarta.persistence.criteria.Predicate;

public final class TicketSpecification {

    private TicketSpecification() {
    }

    public static Specification<Ticket> build(SearchTicketDTO criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            criteria.startFinalPrice().ifPresent(value ->
                    predicates.add(cb.greaterThanOrEqualTo(root.get("finalPrice"), value)));
            criteria.endFinalPrice().ifPresent(value ->
                    predicates.add(cb.lessThanOrEqualTo(root.get("finalPrice"), value)));
            criteria.startBookingTime().ifPresent(value ->
                    predicates.add(cb.greaterThanOrEqualTo(root.get("bookingTime"), value)));
            criteria.endBookingTime().ifPresent(value ->
                    predicates.add(cb.lessThanOrEqualTo(root.get("bookingTime"), value)));
            criteria.isCancelled().ifPresent(value ->
                    predicates.add(cb.equal(root.get("isCancelled"), value)));
            criteria.userId().ifPresent(value ->
                    predicates.add(cb.equal(root.get("user").get("id"), value)));
            criteria.tripId().ifPresent(value ->
                    predicates.add(cb.equal(root.get("trip").get("id"), value)));
            criteria.seatId().ifPresent(value ->
                    predicates.add(cb.equal(root.get("seat").get("id"), value)));

            return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Ticket> bookedSeatsForTrip(int tripId) {
        return (root, query, cb) -> cb.and(
                cb.equal(root.get("trip").get("id"), tripId),
                cb.isFalse(root.get("isCancelled")));
    }
}
