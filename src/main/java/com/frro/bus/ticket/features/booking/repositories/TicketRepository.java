package com.frro.bus.ticket.features.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.booking.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
