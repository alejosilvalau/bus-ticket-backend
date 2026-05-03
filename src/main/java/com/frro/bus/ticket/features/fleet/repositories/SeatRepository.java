package com.frro.bus.ticket.features.fleet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.fleet.entities.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
