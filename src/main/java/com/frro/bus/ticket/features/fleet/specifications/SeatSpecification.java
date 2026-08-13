package com.frro.bus.ticket.features.fleet.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.frro.bus.ticket.features.fleet.dtos.seat.SearchSeatDTO;
import com.frro.bus.ticket.features.fleet.entities.Seat;

import jakarta.persistence.criteria.Predicate;

public final class SeatSpecification {

    private SeatSpecification() {
    }

    public static Specification<Seat> build(SearchSeatDTO criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            criteria.letter().ifPresent(value ->
                    predicates.add(cb.equal(root.get("letter"), value)));
            criteria.number().ifPresent(value ->
                    predicates.add(cb.equal(root.get("number"), value)));
            criteria.isActive().ifPresent(value ->
                    predicates.add(cb.equal(root.get("isActive"), value)));
            criteria.busId().ifPresent(value ->
                    predicates.add(cb.equal(root.get("bus").get("id"), value)));
            criteria.seatTypeId().ifPresent(value ->
                    predicates.add(cb.equal(root.get("seatType").get("id"), value)));

            return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
