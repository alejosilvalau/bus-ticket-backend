package com.frro.bus.ticket.features.fleet.dtos.seat;

import jakarta.validation.constraints.NotBlank;

public record CreateSeatDTO(
        @NotBlank char letter,
        @NotBlank int number,
        @NotBlank int idBus,
        @NotBlank int idSeatType) {
}
