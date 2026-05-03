package com.frro.bus.ticket.features.fleet.services.availability;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;
import com.frro.bus.ticket.features.fleet.mappers.BusMapper;
import com.frro.bus.ticket.features.fleet.mappers.SeatMapper;
import com.frro.bus.ticket.features.fleet.mappers.SeatTypeMapper;
import com.frro.bus.ticket.features.fleet.repositories.BusRepository;
import com.frro.bus.ticket.features.fleet.repositories.SeatRepository;
import com.frro.bus.ticket.features.fleet.repositories.SeatTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {
    private final BusRepository busRepository;
    private final SeatRepository seatRepository;
    private final SeatTypeRepository seatTypeRepository;
    private final BusMapper busMapper;
    private final SeatMapper seatMapper;
    private final SeatTypeMapper seatTypeMapper;

    @Override
    public List<BusDTO> findAllBuses() {
        return busRepository.findAll().stream().map(busMapper::toBusDTO).toList();
    }

    @Override
    public List<SeatDTO> findAllSeats() {
        return seatRepository.findAll().stream().map(seatMapper::toSeatDTO).toList();
    }

    @Override
    public List<SeatTypeDTO> findAllSeatTypes() {
        return seatTypeRepository.findAll().stream().map(seatTypeMapper::toSeatTypeDTO).toList();
    }

    @Override
    public Optional<BusDTO> findBusById(int id) {
        return busRepository.findById(id).map(busMapper::toBusDTO);
    }

    @Override
    public Optional<SeatDTO> findSeatById(int id) {
        return seatRepository.findById(id).map(seatMapper::toSeatDTO);
    }

    @Override
    public Optional<SeatTypeDTO> findSeatTypeById(int id) {
        return seatTypeRepository.findById(id).map(seatTypeMapper::toSeatTypeDTO);
    }
}
