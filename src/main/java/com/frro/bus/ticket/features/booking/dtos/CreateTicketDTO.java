package com.frro.bus.ticket.features.booking.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateTicketDTO(

        @NotNull(message = "User ID is required") @Min(value = 1, message = "User ID must be a positive number") Integer userId,

        @NotNull(message = "Trip ID is required") @Min(value = 1, message = "Trip ID must be a positive number") Integer tripId,

        @NotNull(message = "Seat ID is required") @Min(value = 1, message = "Seat ID must be a positive number") Integer seatId) {
}
