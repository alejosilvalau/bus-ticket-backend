package com.frro.bus.ticket.features.fleet.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.frro.bus.ticket.features.fleet.dtos.seattype.SearchSeatTypeDTO;
import com.frro.bus.ticket.features.fleet.entities.SeatType;

import jakarta.persistence.criteria.Predicate;

public final class SeatTypeSpecification {

    private SeatTypeSpecification() {
    }

    public static Specification<SeatType> build(SearchSeatTypeDTO criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            criteria.name().ifPresent(value ->
                    predicates.add(cb.like(cb.lower(root.get("name")), likePattern(value))));
            criteria.startUpcharge().ifPresent(value ->
                    predicates.add(cb.greaterThanOrEqualTo(root.get("upcharge"), value)));
            criteria.endUpcharge().ifPresent(value ->
                    predicates.add(cb.lessThanOrEqualTo(root.get("upcharge"), value)));

            return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static String likePattern(String value) {
        return "%" + value.toLowerCase() + "%";
    }
}
