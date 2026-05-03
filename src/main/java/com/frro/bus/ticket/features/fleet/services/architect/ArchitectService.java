package com.frro.bus.ticket.features.fleet.services.architect;

import java.util.Optional;

import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.CreateBusDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.UpdateBusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.CreateSeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.UpdateSeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.CreateSeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.UpdateSeatTypeDTO;

public interface ArchitectService {
    BusDTO createBus(CreateBusDTO userRequest);

    SeatDTO createSeat(CreateSeatDTO userRequest);

    SeatTypeDTO createSeatType(CreateSeatTypeDTO userRequest);

    Optional<BusDTO> updateBus(UpdateBusDTO userRequest);

    Optional<SeatDTO> updateSeat(UpdateSeatDTO userRequest);

    Optional<SeatTypeDTO> updateSeatType(UpdateSeatTypeDTO userRequest);

    Optional<BusDTO> deleteBus(int id);

    Optional<SeatDTO> deleteSeat(int id);

    Optional<SeatTypeDTO> deleteSeatType(int id);
}
