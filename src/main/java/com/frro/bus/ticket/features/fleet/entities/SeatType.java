package com.frro.bus.ticket.features.fleet.entities;

import java.math.BigDecimal;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.frro.bus.ticket.common.utils.entities.EntityWithId;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seat_types")
public class SeatType implements EntityWithId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private BigDecimal upcharge = BigDecimal.ZERO;

    @OneToMany(mappedBy = "seatType", fetch = FetchType.LAZY)
    private List<Seat> seats;
}
