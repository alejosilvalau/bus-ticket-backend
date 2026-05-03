package com.frro.bus.ticket.features.fleet.dtos.seat;

import jakarta.validation.constraints.NotBlank;

public record SeatDTO(
        @NotBlank int id,
        // @NotBlank @NotNull int idBus,
        // @NotBlank @NotNull int idSeatType,
        @NotBlank char letter,
        @NotBlank int number) {
}
