package com.frro.bus.ticket.features.identity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.identity.entities.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
}
