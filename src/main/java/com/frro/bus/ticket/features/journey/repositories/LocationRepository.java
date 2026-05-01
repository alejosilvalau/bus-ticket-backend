package com.frro.bus.ticket.features.journey.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.journey.entities.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
}
