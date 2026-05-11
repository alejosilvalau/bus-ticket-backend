package com.frro.bus.ticket.features.fleet.services.availability;

import java.util.List;
import java.util.Optional;

import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.SearchBusDTO;

public interface AvailabilityService {
    List<BusDTO> findAllBuses();

    List<SeatDTO> findAllSeats();

    List<SeatTypeDTO> findAllSeatTypes();

    Optional<BusDTO> findBusById(int id);

    Optional<SeatDTO> findSeatById(int id);

    Optional<SeatTypeDTO> findSeatTypeById(int id);

    List<BusDTO> searchBuses(SearchBusDTO searchCriteria);
}
