package com.frro.bus.ticket.features.fleet.dtos.seat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateSeatDTO(
        @NotNull(message = "Seat letter is required")
        Character letter,

        @NotNull(message = "Seat number is required")
        @Min(value = 1, message = "Seat number must be at least 1")
        int number,

        boolean isActive,

        @NotNull(message = "Bus ID is required")
        @Min(value = 1, message = "Bus ID must be a positive number")
        int busId,

        @NotNull(message = "Seat type ID is required")
        @Min(value = 1, message = "Seat type ID must be a positive number")
        int seatTypeId) {
}
