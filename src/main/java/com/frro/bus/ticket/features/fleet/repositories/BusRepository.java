package com.frro.bus.ticket.features.fleet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.fleet.entities.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {
}
