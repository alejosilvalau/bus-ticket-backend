package com.frro.bus.ticket.features.fleet.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.frro.bus.ticket.features.fleet.dtos.bus.SearchBusDTO;
import com.frro.bus.ticket.features.fleet.entities.Bus;

import jakarta.persistence.criteria.Predicate;

public final class BusSpecification {

    private BusSpecification() {
    }

    public static Specification<Bus> build(SearchBusDTO criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            criteria.plateNumber().ifPresent(value ->
                    predicates.add(cb.like(cb.lower(root.get("plateNumber")), likePattern(value))));
            criteria.startTotalCapacity().ifPresent(value ->
                    predicates.add(cb.greaterThanOrEqualTo(root.get("totalCapacity"), value)));
            criteria.endTotalCapacity().ifPresent(value ->
                    predicates.add(cb.lessThanOrEqualTo(root.get("totalCapacity"), value)));
            criteria.isActive().ifPresent(value ->
                    predicates.add(cb.equal(root.get("isActive"), value)));

            return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    private static String likePattern(String value) {
        return "%" + value.toLowerCase() + "%";
    }
}
