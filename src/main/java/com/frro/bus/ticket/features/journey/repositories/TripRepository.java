package com.frro.bus.ticket.features.journey.repositories;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.journey.entities.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer>, JpaSpecificationExecutor<Trip> {

    @EntityGraph(attributePaths = { "bus", "driver", "locationOrigin", "locationDestination" })
    Page<Trip> findAll(Pageable pageable);

    @EntityGraph(attributePaths = { "bus", "driver", "locationOrigin", "locationDestination" })
    Page<Trip> findAll(Specification<Trip> spec, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = { "bus", "driver", "locationOrigin", "locationDestination" })
    Optional<Trip> findById(Integer id);

    Optional<Trip> findByBusIdAndDepartureDate(Integer busId, ZonedDateTime departureDate);

    Optional<Trip> findByDriverIdAndDepartureDate(Integer driverId, ZonedDateTime departureDate);

    @Query("SELECT COUNT(t) > 0 FROM Trip t WHERE t.driver.id = :driverId AND t.id <> :excludeTripId " +
            "AND t.departureDate < :arrivalDatePlusBuffer AND t.arrivalDate > :departureDateMinusBuffer")
    boolean existsConflictingDriverTrip(
            @Param("driverId") int driverId,
            @Param("excludeTripId") int excludeTripId,
            @Param("departureDateMinusBuffer") ZonedDateTime departureDateMinusBuffer,
            @Param("arrivalDatePlusBuffer") ZonedDateTime arrivalDatePlusBuffer);

    @Query("SELECT COUNT(t) > 0 FROM Trip t WHERE t.bus.id = :busId AND t.id <> :excludeTripId " +
            "AND t.departureDate < :arrivalDatePlusBuffer AND t.arrivalDate > :departureDateMinusBuffer")
    boolean existsConflictingBusTrip(
            @Param("busId") int busId,
            @Param("excludeTripId") int excludeTripId,
            @Param("departureDateMinusBuffer") ZonedDateTime departureDateMinusBuffer,
            @Param("arrivalDatePlusBuffer") ZonedDateTime arrivalDatePlusBuffer);
}
