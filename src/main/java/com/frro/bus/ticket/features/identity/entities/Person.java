package com.frro.bus.ticket.features.identity.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(name = "is_user", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    private java.util.List<com.frro.bus.ticket.trip.model.Trip> tripsAsDriver;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private java.util.List<com.frro.bus.ticket.features.booking.entities.Ticket> tickets;
}
