package com.frro.bus.ticket.features.booking.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.booking.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @EntityGraph(attributePaths = { "user", "trip", "seat" })
    @Override
    List<Ticket> findAll();

    @EntityGraph(attributePaths = { "user", "trip", "seat" })
    @Override
    Optional<Ticket> findById(Integer id);
}
