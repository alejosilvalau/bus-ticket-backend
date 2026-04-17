package com.frro.bus.ticket.seat_type.repository;

import com.frro.bus.ticket.seat_type.model.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatTypeRepository extends JpaRepository<SeatType, Long> {
	boolean existsByName(String name);
}