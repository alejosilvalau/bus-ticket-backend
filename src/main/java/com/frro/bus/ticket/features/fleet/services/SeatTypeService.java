package com.frro.bus.ticket.seat_type.service;

import com.frro.bus.ticket.seat_type.model.SeatType;
import java.util.List;

public interface SeatTypeService {
	List<SeatType> getAllSeatTypes();
	SeatType getSeatTypeById(Long id);
	SeatType createSeatType(SeatType seatType);
	SeatType updateSeatType(Long id, SeatType seatType);
	void deleteSeatType(Long id);
}
