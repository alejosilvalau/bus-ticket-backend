package com.frro.bus.ticket.bus.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
}
