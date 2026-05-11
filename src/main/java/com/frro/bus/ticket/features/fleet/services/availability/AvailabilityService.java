package com.frro.bus.ticket.features.fleet.services.availability;

import java.util.List;
import java.util.Optional;

import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.SearchBusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SearchSeatTypeDTO;

public interface AvailabilityService {
    List<BusDTO> findAllBuses();

    List<BusDTO> searchBuses(SearchBusDTO searchCriteria);

    Optional<BusDTO> findBusById(int id);

    List<SeatDTO> findAllSeats();

    Optional<SeatDTO> findSeatById(int id);

    List<SeatTypeDTO> findAllSeatTypes();

    List<SeatTypeDTO> searchSeatTypes(SearchSeatTypeDTO searchCriteria);

    Optional<SeatTypeDTO> findSeatTypeById(int id);

}
