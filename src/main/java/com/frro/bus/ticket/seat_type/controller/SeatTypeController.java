package com.frro.bus.ticket.seat_type.controller;

import com.frro.bus.ticket.seat_type.dto.SeatTypeDto;
import com.frro.bus.ticket.seat_type.mapper.SeatTypeMapper;
import com.frro.bus.ticket.seat_type.model.SeatType;
import com.frro.bus.ticket.seat_type.service.SeatTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/seat-types")
public class SeatTypeController {
	private final SeatTypeService seatTypeService;

	@Autowired
	public SeatTypeController(SeatTypeService seatTypeService) {
		this.seatTypeService = seatTypeService;
	}

	@GetMapping
	public ResponseEntity<List<SeatTypeDto>> getAllSeatTypes() {
		List<SeatType> seatTypes = seatTypeService.getAllSeatTypes();
		List<SeatTypeDto> seatTypeDtos = seatTypes.stream()
				.map(SeatTypeMapper::toDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(seatTypeDtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SeatTypeDto> getSeatTypeById(@PathVariable Long id) {
		SeatType seatType = seatTypeService.getSeatTypeById(id);
		return ResponseEntity.ok(SeatTypeMapper.toDto(seatType));
	}

	@PostMapping
	public ResponseEntity<SeatTypeDto> createSeatType(@RequestBody SeatTypeDto seatTypeDto) {
		SeatType createdSeatType = seatTypeService.createSeatType(SeatTypeMapper.toEntity(seatTypeDto));
		return ResponseEntity.ok(SeatTypeMapper.toDto(createdSeatType));
	}

	@PutMapping("/{id}")
	public ResponseEntity<SeatTypeDto> updateSeatType(@PathVariable Long id, @RequestBody SeatTypeDto seatTypeDto) {
		SeatType updatedSeatType = seatTypeService.updateSeatType(id, SeatTypeMapper.toEntity(seatTypeDto));
		return ResponseEntity.ok(SeatTypeMapper.toDto(updatedSeatType));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSeatType(@PathVariable Long id) {
		seatTypeService.deleteSeatType(id);
		return ResponseEntity.noContent().build();
	}
}