package com.frro.bus.ticket.features.fleet.services.availability;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;
import com.frro.bus.ticket.features.fleet.entities.Bus;
import com.frro.bus.ticket.features.fleet.entities.Seat;
import com.frro.bus.ticket.features.fleet.entities.SeatType;
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

    private static final ExampleMatcher DEFAULT_MATCHER = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

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

    @Override
    public List<BusDTO> searchBuses(SearchBusDTO searchCriteria) {
        Bus probe = busMapper.toBus(searchCriteria);

        return busRepository.findAll(Example.of(probe, DEFAULT_MATCHER))
                .stream()
                .map(busMapper::toBusDTO)
                .toList();
    }

    @Override
    public List<SeatDTO> searchSeats(SearchSeatDTO searchCriteria) {
        Seat probe = seatMapper.toSeat(searchCriteria);

        return seatRepository.findAll(Example.of(probe, DEFAULT_MATCHER))
                .stream()
                .map(seatMapper::toSeatDTO)
                .toList();
    }

    @Override
    public List<SeatTypeDTO> searchSeatTypes(SearchSeatTypeDTO searchCriteria) {
        SeatType probe = seatTypeMapper.toSeatType(searchCriteria);

        return seatTypeRepository.findAll(Example.of(probe, DEFAULT_MATCHER))
                .stream()
                .map(seatTypeMapper::toSeatTypeDTO)
                .toList();
    }
}
