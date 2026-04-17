package com.frro.bus.ticket.seat_type.mapper;

import com.frro.bus.ticket.seat_type.dto.SeatTypeDto;
import com.frro.bus.ticket.seat_type.model.SeatType;

public class SeatTypeMapper {
	public static SeatTypeDto toDto(SeatType seatType) {
		if (seatType == null) {
			return null;
		}
		return new SeatTypeDto(seatType.getId(), seatType.getName());
	}

	public static SeatType toEntity(SeatTypeDto seatTypeDto) {
		if (seatTypeDto == null) {
			return null;
		}
		SeatType seatType = new SeatType();
		seatType.setId(seatTypeDto.getId());
		seatType.setName(seatTypeDto.getName());
		return seatType;
	}
}
