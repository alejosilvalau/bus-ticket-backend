package com.frro.bus.ticket.features.journey.repositories;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.journey.entities.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

    @Override
    @EntityGraph(attributePaths = { "bus", "driver", "locationOrigin", "locationDestination" })
    List<Trip> findAll();

    @Override
    @EntityGraph(attributePaths = { "bus", "driver", "locationOrigin", "locationDestination" })
    Optional<Trip> findById(Integer id);

    @Query("SELECT t FROM Trip t WHERE " +
            "(:departureDate IS NULL OR t.departureDate >= :departureDate) AND " +
            "(:arrivalDate IS NULL OR t.arrivalDate <= :arrivalDate) AND " +
            "(:startBasePrice IS NULL OR t.basePrice >= :startBasePrice) AND " +
            "(:endBasePrice IS NULL OR t.basePrice <= :endBasePrice) AND " +
            "(:idBus IS NULL OR t.bus.id = :idBus) AND " +
            "(:idDriver IS NULL OR t.driver.id = :idDriver) AND " +
            "(:idLocationOrigin IS NULL OR t.locationOrigin.id = :idLocationOrigin) AND " +
            "(:idLocationDestination IS NULL OR t.locationDestination.id = :idLocationDestination)")
    @EntityGraph(attributePaths = { "bus", "driver", "locationOrigin", "locationDestination" })
    List<Trip> searchTrips(
            @Param("departureDate") ZonedDateTime departureDate,
            @Param("arrivalDate") ZonedDateTime arrivalDate,
            @Param("startBasePrice") BigDecimal startBasePrice,
            @Param("endBasePrice") BigDecimal endBasePrice,
            @Param("idBus") Integer idBus,
            @Param("idDriver") Integer idDriver,
            @Param("idLocationOrigin") Integer idLocationOrigin,
            @Param("idLocationDestination") Integer idLocationDestination);
}
