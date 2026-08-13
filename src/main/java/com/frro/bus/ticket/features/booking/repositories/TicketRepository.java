package com.frro.bus.ticket.features.booking.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.booking.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>, JpaSpecificationExecutor<Ticket> {

    Page<Ticket> findAll(Pageable pageable);

    Page<Ticket> findAll(Specification<Ticket> spec, Pageable pageable);

    @Override
    Optional<Ticket> findById(Integer id);

    Optional<Ticket> findByTripIdAndSeatId(Integer tripId, Integer seatId);

    Optional<Ticket> findByTripIdAndSeatIdAndIsCancelledFalse(Integer tripId, Integer seatId);

    long countByTripIdAndIsCancelledFalse(Integer tripId);
}
