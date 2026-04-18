package com.frro.bus.ticket.trip.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.frro.bus.ticket.features.fleet.entities.Bus;
import com.frro.bus.ticket.features.identity.entities.Person;
import com.frro.bus.ticket.location.model.Location;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bus", nullable = false)
    private Bus bus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_driver", nullable = false)
    private Person driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_location_origin", nullable = false)
    private Location locationOrigin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_location_destination", nullable = false)
    private Location locationDestination;

    @Column(nullable = false)
    private LocalDateTime departureDate;

    @Column(nullable = false)
    private LocalDateTime arrivalDate;

    @Column(nullable = false)
    private BigDecimal basePrice;

    @OneToMany(mappedBy = "trip", fetch = FetchType.LAZY)
    private java.util.List<com.frro.bus.ticket.features.booking.entities.Ticket> tickets;
}
