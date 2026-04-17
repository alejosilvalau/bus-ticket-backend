package com.frro.bus.ticket.bus.repository;

import com.frro.bus.ticket.bus.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {}
