package com.frro.bus.ticket.seat_type.service;

import com.frro.bus.ticket.seat_type.model.SeatType;
import com.frro.bus.ticket.seat_type.repository.SeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatTypeServiceImpl implements SeatTypeService {
	private final SeatTypeRepository seatTypeRepository;

	public SeatTypeServiceImpl(SeatTypeRepository seatTypeRepository) {
		this.seatTypeRepository = seatTypeRepository;
	}

	@Override
	public List<SeatType> getAllSeatTypes() {
		return seatTypeRepository.findAll();
	}

	@Override
	public SeatType getSeatTypeById(Long id) {
		return seatTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Seat type not found"));
	}

	@Override
	public SeatType createSeatType(SeatType seatType) {
		if (seatTypeRepository.existsByName(seatType.getName())) {
			throw new RuntimeException("Seat type with this name already exists");
		}
		return seatTypeRepository.save(seatType);
	}

	@Override
	public SeatType updateSeatType(Long id, SeatType seatType) {
		SeatType existingSeatType = getSeatTypeById(id);
		existingSeatType.setName(seatType.getName());
		return seatTypeRepository.save(existingSeatType);
	}

	@Override
	public void deleteSeatType(Long id) {
		if (!seatTypeRepository.existsById(id)) {
			throw new RuntimeException("Seat type not found");
		}
		seatTypeRepository.deleteById(id);
	}
}
