package com.frro.bus.ticket.features.journey.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import com.frro.bus.ticket.common.utils.entities.EntityWithId;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location", uniqueConstraints = {
        @UniqueConstraint(name = "uk_location_name_state_code", columnNames = { "city_name",
                "state", "postal_code" }),
})
public class Location implements EntityWithId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String cityName;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String postalCode;

    @OneToMany(mappedBy = "locationOrigin", fetch = FetchType.LAZY)
    private List<Trip> tripsOrigin = new ArrayList<Trip>();

    @OneToMany(mappedBy = "locationDestination", fetch = FetchType.LAZY)
    private List<Trip> tripsDestination = new ArrayList<Trip>();;
}
