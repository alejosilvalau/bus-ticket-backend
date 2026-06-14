package com.frro.bus.ticket.features.journey.repositories;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @EntityGraph(attributePaths = { "bus", "driver", "locationOrigin", "locationDestination" })
    Page<Trip> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = { "bus", "driver", "locationOrigin", "locationDestination" })
    Optional<Trip> findById(Integer id);

    Optional<Trip> findByBusIdAndDepartureDate(Integer busId, ZonedDateTime departureDate);

    Optional<Trip> findByDriverIdAndDepartureDate(Integer driverId, ZonedDateTime departureDate);

    @Query("SELECT t FROM Trip t WHERE " +
            "(:departureDate IS NULL OR t.departureDate >= :departureDate) AND " +
            "(:arrivalDate IS NULL OR t.arrivalDate <= :arrivalDate) AND " +
            "(:startBasePrice IS NULL OR t.basePrice >= :startBasePrice) AND " +
            "(:endBasePrice IS NULL OR t.basePrice <= :endBasePrice) AND " +
            "(:busId IS NULL OR t.bus.id = :busId) AND " +
            "(:driverId IS NULL OR t.driver.id = :driverId) AND " +
            "(:locationOriginId IS NULL OR t.locationOrigin.id = :locationOriginId) AND " +
            "(:locationDestinationId IS NULL OR t.locationDestination.id = :locationDestinationId)")
    @EntityGraph(attributePaths = { "bus", "driver", "locationOrigin", "locationDestination" })
    List<Trip> searchTrips(
            @Param("departureDate") ZonedDateTime departureDate,
            @Param("arrivalDate") ZonedDateTime arrivalDate,
            @Param("startBasePrice") BigDecimal startBasePrice,
            @Param("endBasePrice") BigDecimal endBasePrice,
            @Param("busId") Integer busId,
            @Param("driverId") Integer driverId,
            @Param("locationOriginId") Integer locationOriginId,
            @Param("locationDestinationId") Integer locationDestinationId);

    @Query("SELECT t FROM Trip t WHERE " +
            "(:departureDate IS NULL OR t.departureDate >= :departureDate) AND " +
            "(:arrivalDate IS NULL OR t.arrivalDate <= :arrivalDate) AND " +
            "(:startBasePrice IS NULL OR t.basePrice >= :startBasePrice) AND " +
            "(:endBasePrice IS NULL OR t.basePrice <= :endBasePrice) AND " +
            "(:busId IS NULL OR t.bus.id = :busId) AND " +
            "(:driverId IS NULL OR t.driver.id = :driverId) AND " +
            "(:locationOriginId IS NULL OR t.locationOrigin.id = :locationOriginId) AND " +
            "(:locationDestinationId IS NULL OR t.locationDestination.id = :locationDestinationId)")
    @EntityGraph(attributePaths = { "bus", "driver", "locationOrigin", "locationDestination" })
    Page<Trip> searchTrips(
            @Param("departureDate") ZonedDateTime departureDate,
            @Param("arrivalDate") ZonedDateTime arrivalDate,
            @Param("startBasePrice") BigDecimal startBasePrice,
            @Param("endBasePrice") BigDecimal endBasePrice,
            @Param("busId") Integer busId,
            @Param("driverId") Integer driverId,
            @Param("locationOriginId") Integer locationOriginId,
            @Param("locationDestinationId") Integer locationDestinationId,
            Pageable pageable);
}
