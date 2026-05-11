package com.frro.bus.ticket.features.fleet.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT s FROM Seat s WHERE " +
            "(:letter IS NULL OR s.letter = :letter) AND " +
            "(:number IS NULL OR s.number = :number) AND " +
            "(:isActive IS NULL OR s.isActive = :isActive) AND " +
            "(:idBus IS NULL OR s.bus.id = :idBus) AND " +
            "(:idSeatType IS NULL OR s.seatType.id = :idSeatType)")
    @EntityGraph(attributePaths = { "bus", "seatType" })
    List<Seat> searchSeats(
            @Param("letter") Character letter,
            @Param("number") Integer number,
            @Param("isActive") Boolean isActive,
            @Param("idBus") Integer idBus,
            @Param("idSeatType") Integer idSeatType);
}
