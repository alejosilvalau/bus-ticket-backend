package com.frro.bus.ticket.features.journey.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.journey.entities.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
    // findAll
    // findById
    // search
    // create
    // update
    // delete
}
