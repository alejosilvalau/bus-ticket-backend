package com.frro.bus.ticket.features.booking.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.frro.bus.ticket.features.fleet.entities.Seat;
import com.frro.bus.ticket.features.identity.entities.User;
import com.frro.bus.ticket.features.journey.entities.Trip;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// @Table(name = "ticket", uniqueConstraints = @UniqueConstraint(name =
// "uk_trip_seat", columnNames = { "id_trip",
// "id_seat" }))
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private BigDecimal finalPrice = BigDecimal.ZERO;

    @Column(nullable = false)
    private ZonedDateTime bookingTime = ZonedDateTime.now();

    @Column(nullable = false)
    private boolean isCancelled = false;

    @Column(nullable = false, unique = true)
    private String token;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "id_user", nullable = false)
    // private User user;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "id_trip", nullable = false)
    // private Trip trip;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "id_seat", nullable = false)
    // private Seat seat;
}
