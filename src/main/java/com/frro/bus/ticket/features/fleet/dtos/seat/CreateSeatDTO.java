package com.frro.bus.ticket.features.fleet.dtos.seat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateSeatDTO(
        @NotNull Character letter,

        @NotNull @Min(1) Integer number,

        @NotNull Boolean isActive,

        @NotNull @Min(1) Integer busId,

        @NotNull @Min(1) Integer seatTypeId) {
}
