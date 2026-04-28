package com.frro.bus.ticket.features.fleet.dtos.seat;

import com.frro.bus.ticket.features.fleet.entities.Bus;
import com.frro.bus.ticket.features.fleet.entities.SeatType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SeatFullDTO(
        @NotBlank @NotNull int id,
        @NotBlank @NotNull Bus bus,
        @NotBlank @NotNull SeatType seatType,
        @NotBlank @NotNull char letter,
        @NotBlank @NotNull int number) {
}
