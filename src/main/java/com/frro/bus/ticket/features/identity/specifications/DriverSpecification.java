package com.frro.bus.ticket.features.identity.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.frro.bus.ticket.common.utils.StringSearchUtils;
import com.frro.bus.ticket.features.identity.dtos.driver.SearchDriverDTO;
import com.frro.bus.ticket.features.identity.entities.Driver;

import jakarta.persistence.criteria.Predicate;

public final class DriverSpecification {

    private DriverSpecification() {
    }

    public static Specification<Driver> build(SearchDriverDTO criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            criteria.firstName().ifPresent(value ->
                    predicates.add(cb.like(cb.lower(root.get("firstName")), StringSearchUtils.likePattern(value))));
            criteria.lastName().ifPresent(value ->
                    predicates.add(cb.like(cb.lower(root.get("lastName")), StringSearchUtils.likePattern(value))));
            criteria.isActive().ifPresent(value ->
                    predicates.add(cb.equal(root.get("isActive"), value)));
            criteria.licenseNumber().ifPresent(value ->
                    predicates.add(cb.like(cb.lower(root.get("licenseNumber")), StringSearchUtils.likePattern(value))));
            criteria.phoneNumber().ifPresent(value ->
                    predicates.add(cb.like(cb.lower(root.get("phoneNumber")), StringSearchUtils.likePattern(value))));

            return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
