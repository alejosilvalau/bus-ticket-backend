package com.frro.bus.ticket.features.journey.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.frro.bus.ticket.features.fleet.entities.Bus;
import com.frro.bus.ticket.features.identity.entities.Driver;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trip", uniqueConstraints = {
        @UniqueConstraint(name = "uk_trip_bus_departure", columnNames = { "id_bus", "departure_date" }),
        @UniqueConstraint(name = "uk_trip_driver_departure", columnNames = { "id_driver", "departure_date" })
})
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private ZonedDateTime departureDate;

    @Column(nullable = false)
    private ZonedDateTime arrivalDate;

    @Column(nullable = false)
    private BigDecimal basePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bus", nullable = false)
    private Bus bus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_driver", nullable = false)
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_location_origin", nullable = false)
    private Location locationOrigin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_location_destination", nullable = false)
    private Location locationDestination;
}
