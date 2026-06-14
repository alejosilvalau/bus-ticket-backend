package com.frro.bus.ticket.features.fleet.services.availability;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.frro.bus.ticket.common.exceptions.ResourceNotFoundException;
import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatFullDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.SearchBusDTO;
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
    public Page<BusDTO> findAllBuses(Pageable pageable) {
        return busRepository.findAll(pageable).map(busMapper::toBusDTO);
    }

    @Override
    public Page<BusDTO> searchBuses(SearchBusDTO searchCriteria, Pageable pageable) {
        return busRepository.searchBuses(
                searchCriteria.plateNumber().orElse(null),
                searchCriteria.startTotalCapacity().orElse(null),
                searchCriteria.endTotalCapacity().orElse(null),
                searchCriteria.isActive().orElse(null),
                pageable)
                .map(busMapper::toBusDTO);
    }

    @Override
    public BusDTO findBusById(int id) {
        return busRepository.findById(id)
                .map(busMapper::toBusDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Bus", "id", id));
    }

    @Override
    public Page<SeatFullDTO> findAllSeats(Pageable pageable) {
        return seatRepository.findAll(pageable).map(seatMapper::toSeatFullDTO);
    }

    @Override
    public Page<SeatFullDTO> searchSeats(SearchSeatDTO searchCriteria, Pageable pageable) {
        return seatRepository.searchSeats(
                searchCriteria.letter().orElse(null),
                searchCriteria.number().orElse(null),
                searchCriteria.isActive().orElse(null),
                searchCriteria.idBus().orElse(null),
                searchCriteria.idSeatType().orElse(null),
                pageable)
                .map(seatMapper::toSeatFullDTO);
    }

    @Override
    public SeatFullDTO findSeatById(int id) {
        return seatRepository.findById(id)
                .map(seatMapper::toSeatFullDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Seat", "id", id));
    }

    @Override
    public Page<SeatTypeDTO> findAllSeatTypes(Pageable pageable) {
        return seatTypeRepository.findAll(pageable).map(seatTypeMapper::toSeatTypeDTO);
    }

    @Override
    public Page<SeatTypeDTO> searchSeatTypes(SearchSeatTypeDTO searchCriteria, Pageable pageable) {
        return seatTypeRepository.searchSeatTypes(
                searchCriteria.name().orElse(null),
                searchCriteria.startUpcharge().orElse(null),
                searchCriteria.endUpcharge().orElse(null),
                pageable)
                .map(seatTypeMapper::toSeatTypeDTO);
    }

    @Override
    public SeatTypeDTO findSeatTypeById(int id) {
        return seatTypeRepository.findById(id)
                .map(seatTypeMapper::toSeatTypeDTO)
                .orElseThrow(() -> new ResourceNotFoundException("SeatType", "id", id));
    }
}
