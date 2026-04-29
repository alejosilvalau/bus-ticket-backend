package com.frro.bus.ticket.features.fleet.dtos.seat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SeatDTO(
        @NotBlank @NotNull int id,
        // @NotBlank @NotNull int idBus,
        // @NotBlank @NotNull int idSeatType,
        @NotBlank @NotNull char letter,
        @NotBlank @NotNull int number) {
}
