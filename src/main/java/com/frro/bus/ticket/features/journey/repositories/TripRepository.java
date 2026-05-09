package com.frro.bus.ticket.features.journey.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.journey.entities.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

    @EntityGraph(attributePaths = { "bus", "driver", "locationOrigin", "locationDestination" })
    @Override
    List<Trip> findAll();

    @EntityGraph(attributePaths = { "bus", "driver", "locationOrigin", "locationDestination" })
    @Override
    Optional<Trip> findById(Integer id);

    @EntityGraph(attributePaths = { "bus", "driver", "locationOrigin", "locationDestination" })
    <S extends Trip> List<S> findAll(Example<S> example);
}
