package com.frro.bus.ticket.features.fleet.services.availability;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.frro.bus.ticket.common.dto.PageResponse;
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
    public PageResponse<BusDTO> findAllBuses(Pageable pageable) {
        Page<BusDTO> page = busRepository.findAll(pageable).map(busMapper::toBusDTO);
        return toPageResponse(page);
    }

    @Override
    public PageResponse<BusDTO> searchBuses(SearchBusDTO searchCriteria, Pageable pageable) {
        Page<BusDTO> page = busRepository.searchBuses(
                searchCriteria.plateNumber().orElse(null),
                searchCriteria.startTotalCapacity().orElse(null),
                searchCriteria.endTotalCapacity().orElse(null),
                searchCriteria.isActive().orElse(null),
                pageable)
                .map(busMapper::toBusDTO);
        return toPageResponse(page);
    }

    @Override
    public BusDTO findBusById(int id) {
        return busRepository.findById(id)
                .map(busMapper::toBusDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Bus", "id", id));
    }

    @Override
    public PageResponse<SeatFullDTO> findAllSeats(Pageable pageable) {
        Page<SeatFullDTO> page = seatRepository.findAll(pageable).map(seatMapper::toSeatFullDTO);
        return toPageResponse(page);
    }

    @Override
    public PageResponse<SeatFullDTO> searchSeats(SearchSeatDTO searchCriteria, Pageable pageable) {
        Page<SeatFullDTO> page = seatRepository.searchSeats(
                searchCriteria.letter().orElse(null),
                searchCriteria.number().orElse(null),
                searchCriteria.isActive().orElse(null),
                searchCriteria.busId().orElse(null),
                searchCriteria.seatTypeId().orElse(null),
                pageable)
                .map(seatMapper::toSeatFullDTO);
        return toPageResponse(page);
    }

    @Override
    public SeatFullDTO findSeatById(int id) {
        return seatRepository.findById(id)
                .map(seatMapper::toSeatFullDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Seat", "id", id));
    }

    @Override
    public PageResponse<SeatTypeDTO> findAllSeatTypes(Pageable pageable) {
        Page<SeatTypeDTO> page = seatTypeRepository.findAll(pageable).map(seatTypeMapper::toSeatTypeDTO);
        return toPageResponse(page);
    }

    @Override
    public PageResponse<SeatTypeDTO> searchSeatTypes(SearchSeatTypeDTO searchCriteria, Pageable pageable) {
        Page<SeatTypeDTO> page = seatTypeRepository.searchSeatTypes(
                searchCriteria.name().orElse(null),
                searchCriteria.startUpcharge().orElse(null),
                searchCriteria.endUpcharge().orElse(null),
                pageable)
                .map(seatTypeMapper::toSeatTypeDTO);
        return toPageResponse(page);
    }

    @Override
    public SeatTypeDTO findSeatTypeById(int id) {
        return seatTypeRepository.findById(id)
                .map(seatTypeMapper::toSeatTypeDTO)
                .orElseThrow(() -> new ResourceNotFoundException("SeatType", "id", id));
    }

    private <T> PageResponse<T> toPageResponse(Page<T> page) {
        return PageResponse.of(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.isEmpty());
    }
}
