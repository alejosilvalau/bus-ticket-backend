package com.frro.bus.ticket.trip.repository;

import com.frro.bus.ticket.trip.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {}
