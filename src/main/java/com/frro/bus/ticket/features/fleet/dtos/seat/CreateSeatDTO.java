package com.frro.bus.ticket.features.fleet.dtos.seat;

import jakarta.validation.constraints.NotBlank;

public record CreateSeatDTO(
        // @NotBlank @NotNull int idBus,
        // @NotBlank @NotNull int idSeatType,
        @NotBlank char letter,
        @NotBlank int number) {
}
