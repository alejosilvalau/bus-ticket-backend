package com.frro.bus.ticket.features.journey.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private List<Trip> tripsOrigin;

    // For trips where this location is the destination
    @OneToMany(mappedBy = "locationDestination", fetch = FetchType.LAZY)
    private List<Trip> tripsDestination;
}
