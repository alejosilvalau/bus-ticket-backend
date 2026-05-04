package com.frro.bus.ticket.features.fleet.entities;

import jakarta.persistence.*;
import java.util.List;

import com.frro.bus.ticket.common.utils.EntityWithId;
import com.frro.bus.ticket.features.booking.entities.Ticket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seat", uniqueConstraints = @UniqueConstraint(name = "uk_bus_seat", columnNames = { "id_bus", "letter",
        "number" }))
public class Seat implements EntityWithId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private char letter;

    @Column(nullable = false)
    private int number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bus", nullable = false)
    private Bus bus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_seat_type", nullable = false)
    private SeatType seatType;

    @OneToMany(mappedBy = "seat", fetch = FetchType.LAZY)
    private List<Ticket> tickets;
}
