package com.frro.bus.ticket.features.identity.entities;

import java.util.ArrayList;
import java.util.List;

import com.frro.bus.ticket.features.journey.entities.Trip;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("0") // 0 for False (Driver)
public class Driver extends Person {
    @Column(nullable = true, unique = true)
    private String licenseNumber;

    @Column(nullable = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    private List<Trip> trips = new ArrayList<>();
}
