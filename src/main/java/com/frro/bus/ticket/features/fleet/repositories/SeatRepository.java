package com.frro.bus.ticket.features.fleet.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.fleet.entities.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer>, JpaSpecificationExecutor<Seat> {

    @EntityGraph(attributePaths = { "bus", "seatType" })
    Page<Seat> findAll(Pageable pageable);

    @EntityGraph(attributePaths = { "bus", "seatType" })
    Page<Seat> findAll(Specification<Seat> spec, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = { "bus", "seatType" })
    Optional<Seat> findById(Integer id);

    Optional<Seat> findByBusIdAndLetterAndNumber(Integer busId, Character letter, Integer number);

    @EntityGraph(attributePaths = { "bus", "seatType" })
    List<Seat> findByBusIdAndIsActiveTrue(int busId);
}
