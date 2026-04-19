package com.frro.bus.ticket.features.fleet.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;
import com.frro.bus.ticket.features.journey.entities.Trip;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String plateNumber;

    @Column(nullable = false)
    private Integer totalCapacity;

    @Column(nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "bus", fetch = FetchType.LAZY)
    private List<Trip> trips;

    @OneToMany(mappedBy = "bus", fetch = FetchType.LAZY)
    private List<Seat> seats;
}
