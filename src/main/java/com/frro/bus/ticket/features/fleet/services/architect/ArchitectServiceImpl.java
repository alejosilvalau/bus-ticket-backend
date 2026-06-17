package com.frro.bus.ticket.features.fleet.services.architect;

import org.springframework.stereotype.Service;

import com.frro.bus.ticket.common.exceptions.DuplicateResourceException;
import com.frro.bus.ticket.common.exceptions.ResourceNotFoundException;
import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.CreateBusDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.UpdateBusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatFullDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.CreateSeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.UpdateSeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.CreateSeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.UpdateSeatTypeDTO;
import com.frro.bus.ticket.features.fleet.entities.Bus;
import com.frro.bus.ticket.features.fleet.entities.Seat;
import com.frro.bus.ticket.features.fleet.entities.SeatType;
import com.frro.bus.ticket.features.fleet.mappers.BusMapper;
import com.frro.bus.ticket.features.fleet.mappers.SeatMapper;
import com.frro.bus.ticket.features.fleet.mappers.SeatTypeMapper;
import com.frro.bus.ticket.features.fleet.repositories.BusRepository;
import com.frro.bus.ticket.features.fleet.repositories.SeatRepository;
import com.frro.bus.ticket.features.fleet.repositories.SeatTypeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArchitectServiceImpl implements ArchitectService {
    private final BusRepository busRepository;
    private final SeatRepository seatRepository;
    private final SeatTypeRepository seatTypeRepository;
    private final BusMapper busMapper;
    private final SeatMapper seatMapper;
    private final SeatTypeMapper seatTypeMapper;

    @Override
    public BusDTO createBus(CreateBusDTO busRequest) {
        busRepository.findByPlateNumber(busRequest.plateNumber())
                .ifPresent(bus -> {
                    throw new DuplicateResourceException("Bus", "plateNumber", busRequest.plateNumber());
                });

        Bus bus = busMapper.toBus(busRequest);
        Bus savedBus = busRepository.save(bus);
        return busMapper.toBusDTO(savedBus);
    }

    @Override
    public BusDTO updateBus(UpdateBusDTO busRequest) {
        Bus existingBus = busRepository.findById(busRequest.id())
                .orElseThrow(() -> new ResourceNotFoundException("Bus", "id", busRequest.id()));

        busRequest.plateNumber().ifPresent(newPlate -> {
            busRepository.findByPlateNumber(newPlate)
                    .filter(found -> found.getId() != busRequest.id())
                    .ifPresent(bus -> {
                        throw new DuplicateResourceException("Bus", "plateNumber", newPlate);
                    });
            existingBus.setPlateNumber(newPlate);
        });

        busRequest.totalCapacity().ifPresent(existingBus::setTotalCapacity);
        busRequest.isActive().ifPresent(existingBus::setActive);

        Bus savedBus = busRepository.save(existingBus);
        return busMapper.toBusDTO(savedBus);
    }

    @Override
    public BusDTO deleteBus(int id) {
        Bus existingBus = busRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus", "id", id));
        busRepository.delete(existingBus);
        return busMapper.toBusDTO(existingBus);
    }

    @Override
    public SeatFullDTO createSeat(CreateSeatDTO seatRequest) {
        Bus bus = busRepository.findById(seatRequest.busId())
                .orElseThrow(() -> new ResourceNotFoundException("Bus", "id", seatRequest.busId()));
        SeatType seatType = seatTypeRepository.findById(seatRequest.seatTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("SeatType", "id", seatRequest.seatTypeId()));

        seatRepository.findByBusIdAndLetterAndNumber(seatRequest.busId(), seatRequest.letter(), seatRequest.number())
                .ifPresent(seat -> {
                    throw new DuplicateResourceException("Seat", "bus+letter+number",
                            "bus=" + seatRequest.busId() + ", " + seatRequest.letter() + seatRequest.number());
                });

        Seat seat = seatMapper.toSeat(seatRequest);
        seat.setBus(bus);
        seat.setSeatType(seatType);
        Seat savedSeat = seatRepository.save(seat);
        return seatMapper.toSeatFullDTO(savedSeat);
    }

    @Override
    @Transactional
    public SeatFullDTO updateSeat(UpdateSeatDTO seatRequest) {
        Seat existingSeat = seatRepository.findById(seatRequest.id())
                .orElseThrow(() -> new ResourceNotFoundException("Seat", "id", seatRequest.id()));

        seatRequest.letter().ifPresent(existingSeat::setLetter);
        seatRequest.number().ifPresent(existingSeat::setNumber);
        seatRequest.isActive().ifPresent(existingSeat::setActive);

        seatRequest.busId().ifPresent(busId -> {
            Bus bus = busRepository.findById(busId)
                    .orElseThrow(() -> new ResourceNotFoundException("Bus", "id", busId));
            existingSeat.setBus(bus);
        });

        seatRequest.seatTypeId().ifPresent(seatTypeId -> {
            SeatType seatType = seatTypeRepository.findById(seatTypeId)
                    .orElseThrow(() -> new ResourceNotFoundException("SeatType", "id", seatTypeId));
            existingSeat.setSeatType(seatType);
        });

        Seat savedSeat = seatRepository.save(existingSeat);
        return seatMapper.toSeatFullDTO(savedSeat);
    }

    @Override
    public SeatFullDTO deleteSeat(int id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seat", "id", id));
        seatRepository.deleteById(id);
        return seatMapper.toSeatFullDTO(seat);
    }

    @Override
    public SeatTypeDTO createSeatType(CreateSeatTypeDTO seatTypeRequest) {
        seatTypeRepository.findByName(seatTypeRequest.name())
                .ifPresent(seatType -> {
                    throw new DuplicateResourceException("SeatType", "name", seatTypeRequest.name());
                });

        SeatType seatType = seatTypeMapper.toSeatType(seatTypeRequest);
        SeatType savedSeatType = seatTypeRepository.save(seatType);
        return seatTypeMapper.toSeatTypeDTO(savedSeatType);
    }

    @Override
    public SeatTypeDTO updateSeatType(UpdateSeatTypeDTO seatTypeRequest) {
        SeatType existingSeatType = seatTypeRepository.findById(seatTypeRequest.id())
                .orElseThrow(() -> new ResourceNotFoundException("SeatType", "id", seatTypeRequest.id()));

        seatTypeRequest.name().ifPresent(newName -> {
            seatTypeRepository.findByName(newName)
                    .filter(found -> found.getId() != seatTypeRequest.id())
                    .ifPresent(seatType -> {
                        throw new DuplicateResourceException("SeatType", "name", newName);
                    });
        });

        seatTypeRequest.name().ifPresent(existingSeatType::setName);
        seatTypeRequest.upcharge().ifPresent(existingSeatType::setUpcharge);

        SeatType savedSeatType = seatTypeRepository.save(existingSeatType);
        return seatTypeMapper.toSeatTypeDTO(savedSeatType);
    }

    @Override
    public SeatTypeDTO deleteSeatType(int id) {
        SeatType seatType = seatTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SeatType", "id", id));
        seatTypeRepository.deleteById(id);
        return seatTypeMapper.toSeatTypeDTO(seatType);
    }
}
