package com.frro.bus.ticket.features.fleet.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bus", nullable = false)
    private com.frro.bus.ticket.bus.model.Bus bus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_seat_type", nullable = false)
    private SeatType seatType;
    
    @Column(nullable = false)
    private char letter;

    @Column(nullable = false)
    private int number;

    @OneToMany(mappedBy = "seat", fetch = FetchType.LAZY)
    private java.util.List<com.frro.bus.ticket.features.booking.entities.Ticket> tickets;
}