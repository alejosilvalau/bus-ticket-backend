package com.frro.bus.ticket.features.fleet.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.fleet.entities.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    @EntityGraph(attributePaths = { "bus", "seatType" })
    Page<Seat> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = { "bus", "seatType" })
    Optional<Seat> findById(Integer id);

    Optional<Seat> findByBusIdAndLetterAndNumber(Integer busId, Character letter, Integer number);

    @EntityGraph(attributePaths = { "bus", "seatType" })
    List<Seat> findByBusIdAndIsActiveTrue(int busId);

    @Query("SELECT s FROM Seat s WHERE " +
            "(:letter IS NULL OR s.letter = :letter) AND " +
            "(:number IS NULL OR s.number = :number) AND " +
            "(:isActive IS NULL OR s.isActive = :isActive) AND " +
            "(:busId IS NULL OR s.bus.id = :busId) AND " +
            "(:seatTypeId IS NULL OR s.seatType.id = :seatTypeId)")
    @EntityGraph(attributePaths = { "bus", "seatType" })
    Page<Seat> searchSeats(
            @Param("letter") Character letter,
            @Param("number") Integer number,
            @Param("isActive") Boolean isActive,
            @Param("busId") Integer busId,
            @Param("seatTypeId") Integer seatTypeId,
            Pageable pageable);
}
