package com.frro.bus.ticket.features.journey.repositories;

import java.time.ZonedDateTime;
import java.util.Optional;

import jakarta.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.journey.entities.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer>, JpaSpecificationExecutor<Trip> {

    Page<Trip> findAll(Pageable pageable);

    Page<Trip> findAll(Specification<Trip> spec, Pageable pageable);

    @Override
    Optional<Trip> findById(Integer id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select t from Trip t where t.id = :id")
    Optional<Trip> findWithLockingById(@Param("id") int id);

    Optional<Trip> findByBusIdAndDepartureDate(Integer busId, ZonedDateTime departureDate);

    Optional<Trip> findByDriverIdAndDepartureDate(Integer driverId, ZonedDateTime departureDate);
}
