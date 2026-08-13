package com.frro.bus.ticket.features.fleet.services.availability;

import org.springframework.data.domain.Pageable;

import com.frro.bus.ticket.common.dto.PageResponse;
import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatFullDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.SearchBusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SearchSeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SearchSeatDTO;

public interface AvailabilityService {
    PageResponse<BusDTO> findAllBuses(Pageable pageable);

    PageResponse<BusDTO> searchBuses(SearchBusDTO searchCriteria, Pageable pageable);

    BusDTO findBusById(int id);

    PageResponse<SeatFullDTO> findAllSeats(Pageable pageable);

    PageResponse<SeatFullDTO> searchSeats(SearchSeatDTO searchCriteria, Pageable pageable);

    SeatFullDTO findSeatById(int id);

    PageResponse<SeatTypeDTO> findAllSeatTypes(Pageable pageable);

    PageResponse<SeatTypeDTO> searchSeatTypes(SearchSeatTypeDTO searchCriteria, Pageable pageable);

    SeatTypeDTO findSeatTypeById(int id);
}
