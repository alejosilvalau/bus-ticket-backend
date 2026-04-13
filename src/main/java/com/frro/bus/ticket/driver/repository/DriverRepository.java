package com.frro.bus.ticket.driver.repository;

import com.frro.bus.ticket.driver.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    // You can add custom finder methods here if needed
}