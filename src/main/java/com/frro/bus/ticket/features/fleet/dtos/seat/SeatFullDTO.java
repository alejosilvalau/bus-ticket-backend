package com.frro.bus.ticket.features.fleet.dtos.seat;

// import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
// import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;

import jakarta.validation.constraints.NotBlank;

public record SeatFullDTO(
        @NotBlank int id,
        // @NotBlank @NotNull BusDTO bus,
        // @NotBlank @NotNull SeatTypeDTO seatType,
        @NotBlank char letter,
        @NotBlank int number) {
}
