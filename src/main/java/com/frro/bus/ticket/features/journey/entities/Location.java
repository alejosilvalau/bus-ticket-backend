package com.frro.bus.ticket.location.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String cityName;

    @Column(nullable = false)
    private String state;

    // For trips where this location is the origin
    @OneToMany(mappedBy = "locationOrigin", fetch = FetchType.LAZY)
    private java.util.List<com.frro.bus.ticket.trip.model.Trip> tripsOrigin;

    // For trips where this location is the destination
    @OneToMany(mappedBy = "locationDestination", fetch = FetchType.LAZY)
    private java.util.List<com.frro.bus.ticket.trip.model.Trip> tripsDestination;
}
