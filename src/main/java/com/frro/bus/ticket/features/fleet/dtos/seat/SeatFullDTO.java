package com.frro.bus.ticket.features.fleet.dtos.seat;

import com.frro.bus.ticket.features.fleet.dtos.bus.BusDTO;
import com.frro.bus.ticket.features.fleet.dtos.seattype.SeatTypeDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SeatFullDTO(
        @NotBlank @NotNull int id,
        @NotBlank @NotNull BusDTO bus,
        @NotBlank @NotNull SeatTypeDTO seatType,
        @NotBlank @NotNull char letter,
        @NotBlank @NotNull int number) {
}
