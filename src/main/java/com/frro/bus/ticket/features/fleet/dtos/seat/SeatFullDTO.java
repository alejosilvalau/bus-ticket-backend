package com.frro.bus.ticket.features.fleet.dtos.seat;

import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;

public record SeatFullDTO(
        int id,
        char letter,
        int number,
        boolean isActive,
        BusDTO bus,
        SeatTypeDTO seatType) {
}
