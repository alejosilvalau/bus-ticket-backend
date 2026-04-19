package com.frro.bus.ticket.features.booking.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.frro.bus.ticket.features.journey.entities.Trip;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private com.frro.bus.ticket.features.identity.entities.Person user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trip", nullable = false)
    private Trip trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_seat", nullable = false)
    private com.frro.bus.ticket.features.fleet.entities.Seat seat;

    @Column(nullable = false)
    private BigDecimal finalPrice;

    @Column(nullable = false)
    private LocalDateTime bookingTime;

    @Column(nullable = false)
    private boolean isCancelled;

    @Column(nullable = false, unique = true)
    private String token;
}
