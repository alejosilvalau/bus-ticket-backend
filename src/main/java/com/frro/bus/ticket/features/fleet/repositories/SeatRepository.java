package com.frro.bus.ticket.features.fleet.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.fleet.entities.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    @EntityGraph(attributePaths = { "bus", "seatType" })
    @Override
    List<Seat> findAll();

    @EntityGraph(attributePaths = { "bus", "seatType" })
    @Override
    Optional<Seat> findById(Integer id);
}
