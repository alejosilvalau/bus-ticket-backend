package com.frro.bus.ticket.seat_type.dto;

public class SeatTypeDto {
	private Long id;
	private String name;

	public SeatTypeDto() {
	}

	public SeatTypeDto(Long id, String name) {
		this.id = id;
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
