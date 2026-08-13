package com.frro.bus.ticket.features.journey.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.journey.entities.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer>, JpaSpecificationExecutor<Location> {
    Optional<Location> findByCityNameAndStateAndPostalCode(String cityName, String state, String postalCode);
}
