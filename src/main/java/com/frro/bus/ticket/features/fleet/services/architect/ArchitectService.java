package com.frro.bus.ticket.features.fleet.services.architect;

import java.util.Optional;

import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.CreateBusDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.UpdateBusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.CreateSeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.SeatFullDTO;
import com.frro.bus.ticket.features.fleet.dtos.seat.UpdateSeatDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.CreateSeatTypeDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.UpdateSeatTypeDTO;

public interface ArchitectService {
    BusDTO createBus(CreateBusDTO userRequest);

    Optional<BusDTO> updateBus(UpdateBusDTO userRequest);

    Optional<BusDTO> deleteBus(int id);

    SeatFullDTO createSeat(CreateSeatDTO userRequest);

    Optional<SeatFullDTO> updateSeat(UpdateSeatDTO userRequest);

    Optional<SeatFullDTO> deleteSeat(int id);

    SeatTypeDTO createSeatType(CreateSeatTypeDTO userRequest);

    Optional<SeatTypeDTO> updateSeatType(UpdateSeatTypeDTO userRequest);

    Optional<SeatTypeDTO> deleteSeatType(int id);
}
