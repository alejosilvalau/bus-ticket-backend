package com.frro.bus.ticket.features.fleet.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.fleet.entities.SeatType;

@Repository
public interface SeatTypeRepository extends JpaRepository<SeatType, Integer>, JpaSpecificationExecutor<SeatType> {
    Optional<SeatType> findByName(String name);
}
