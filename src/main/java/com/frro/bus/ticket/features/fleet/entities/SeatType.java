package com.frro.bus.ticket.features.fleet.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "seat_types")
public class SeatType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

    @OneToMany(mappedBy = "seatType", fetch = FetchType.LAZY)
    private java.util.List<com.frro.bus.ticket.features.fleet.entities.Seat> seats;

	public SeatType() {
	}

	public SeatType(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
