package com.frro.bus.ticket.features.fleet.services.availability;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.SearchBusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatFullDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SearchSeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SearchSeatDTO;
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
    public List<BusDTO> searchBuses(SearchBusDTO searchCriteria) {
        return busRepository.searchBuses(
                searchCriteria.plateNumber().orElse(null),
                searchCriteria.startTotalCapacity().orElse(null),
                searchCriteria.endTotalCapacity().orElse(null),
                searchCriteria.isActive().orElse(null))
                .stream()
                .map(busMapper::toBusDTO)
                .toList();
    }

    @Override
    public Optional<BusDTO> findBusById(int id) {
        return busRepository.findById(id).map(busMapper::toBusDTO);
    }

    @Override
    public List<SeatFullDTO> findAllSeats() {
        return seatRepository.findAll().stream().map(seatMapper::toSeatFullDTO).toList();
    }

    @Override
    public List<SeatFullDTO> searchSeats(SearchSeatDTO searchCriteria) {
        return seatRepository.searchSeats(
                searchCriteria.letter().orElse(null),
                searchCriteria.number().orElse(null),
                searchCriteria.isActive().orElse(null),
                searchCriteria.idBus().orElse(null),
                searchCriteria.idSeatType().orElse(null))
                .stream()
                .map(seatMapper::toSeatFullDTO)
                .toList();
    }

    @Override
    public Optional<SeatFullDTO> findSeatById(int id) {
        return seatRepository.findById(id).map(seatMapper::toSeatFullDTO);
    }

    @Override
    public List<SeatTypeDTO> findAllSeatTypes() {
        return seatTypeRepository.findAll().stream().map(seatTypeMapper::toSeatTypeDTO).toList();
    }

    @Override
    public List<SeatTypeDTO> searchSeatTypes(SearchSeatTypeDTO searchCriteria) {
        return seatTypeRepository.searchSeatTypes(
                searchCriteria.name().orElse(null),
                searchCriteria.startUpcharge().orElse(null),
                searchCriteria.endUpcharge().orElse(null))
                .stream()
                .map(seatTypeMapper::toSeatTypeDTO)
                .toList();
    }

    @Override
    public Optional<SeatTypeDTO> findSeatTypeById(int id) {
        return seatTypeRepository.findById(id).map(seatTypeMapper::toSeatTypeDTO);
    }
}
