package com.frro.bus.ticket.features.fleet.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.fleet.entities.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {
    Optional<Bus> findByPlateNumber(String plateNumber);

    @Query("SELECT b FROM Bus b WHERE " +
            "(:plateNumber IS NULL OR LOWER(b.plateNumber) LIKE LOWER(CONCAT('%', :plateNumber, '%'))) AND " +
            "(:startTotalCapacity IS NULL OR b.totalCapacity >= :startTotalCapacity) AND " +
            "(:endTotalCapacity IS NULL OR b.totalCapacity <= :endTotalCapacity) AND " +
            "(:isActive IS NULL OR b.isActive = :isActive)")
    Page<Bus> searchBuses(
            @Param("plateNumber") String plateNumber,
            @Param("startTotalCapacity") Integer startTotalCapacity,
            @Param("endTotalCapacity") Integer endTotalCapacity,
            @Param("isActive") Boolean isActive,
            Pageable pageable);
}
