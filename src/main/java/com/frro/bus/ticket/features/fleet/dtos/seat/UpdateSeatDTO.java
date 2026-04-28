package com.frro.bus.ticket.features.fleet.dtos.seat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateSeatDTO(
        @NotBlank @NotNull int id,
        int idBus,
        int idSeatType,
        char letter,
        int number) {
}
