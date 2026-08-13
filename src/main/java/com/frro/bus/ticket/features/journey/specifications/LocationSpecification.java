package com.frro.bus.ticket.features.journey.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.frro.bus.ticket.features.journey.dtos.location.SearchLocationDTO;
import com.frro.bus.ticket.features.journey.entities.Location;

import jakarta.persistence.criteria.Predicate;

public final class LocationSpecification {

    private LocationSpecification() {
    }

    public static Specification<Location> build(SearchLocationDTO criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            criteria.cityName().ifPresent(value ->
                    predicates.add(cb.like(cb.lower(root.get("cityName")), likePattern(value))));
            criteria.state().ifPresent(value ->
                    predicates.add(cb.like(cb.lower(root.get("state")), likePattern(value))));
            criteria.postalCode().ifPresent(value ->
                    predicates.add(cb.like(cb.lower(root.get("postalCode")), likePattern(value))));

            return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static String likePattern(String value) {
        return "%" + value.toLowerCase() + "%";
    }
}
