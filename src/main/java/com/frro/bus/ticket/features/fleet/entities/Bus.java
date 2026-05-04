package com.frro.bus.ticket.features.fleet.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;
import com.frro.bus.ticket.features.journey.entities.Trip;
import com.frro.bus.ticket.common.utils.EntityWithId;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bus")
public class Bus implements EntityWithId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String plateNumber;

    @Column(nullable = false)
    private int totalCapacity = 0;

    @Column(nullable = false)
    private boolean isActive = true;

    @OneToMany(mappedBy = "bus", fetch = FetchType.LAZY)
    private List<Trip> trips;

    @OneToMany(mappedBy = "bus", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Seat> seats;
}
