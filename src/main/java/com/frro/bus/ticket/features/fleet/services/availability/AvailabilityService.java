package com.frro.bus.ticket.features.fleet.services.availability;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatFullDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.SearchBusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SearchSeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SearchSeatDTO;

public interface AvailabilityService {
    Page<BusDTO> findAllBuses(Pageable pageable);

    Page<BusDTO> searchBuses(SearchBusDTO searchCriteria, Pageable pageable);

    BusDTO findBusById(int id);

    Page<SeatFullDTO> findAllSeats(Pageable pageable);

    Page<SeatFullDTO> searchSeats(SearchSeatDTO searchCriteria, Pageable pageable);

    SeatFullDTO findSeatById(int id);

    Page<SeatTypeDTO> findAllSeatTypes(Pageable pageable);

    Page<SeatTypeDTO> searchSeatTypes(SearchSeatTypeDTO searchCriteria, Pageable pageable);

    SeatTypeDTO findSeatTypeById(int id);
}
