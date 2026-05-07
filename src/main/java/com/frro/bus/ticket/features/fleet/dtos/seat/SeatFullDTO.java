package com.frro.bus.ticket.features.fleet.dtos.seat;

import java.util.List;

import com.frro.bus.ticket.features.booking.dtos.TicketDTO;
import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;

import jakarta.validation.constraints.NotBlank;

public record SeatFullDTO(
        @NotBlank int id,
        @NotBlank char letter,
        @NotBlank int number,
        @NotBlank BusDTO bus,
        @NotBlank SeatTypeDTO seatType,
        List<TicketDTO> tickets) {
}
