package com.frro.bus.ticket.features.booking.dtos;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTicketDTO(
        @NotNull(message = "Final price is required")
        @DecimalMin(value = "0", message = "Final price must be zero or positive")
        BigDecimal finalPrice,

        @NotNull(message = "Booking time is required")
        ZonedDateTime bookingTime,

        boolean isCancelled,

        @NotBlank(message = "Token is required")
        @Size(min = 1, max = 100, message = "Token must be between 1 and 100 characters")
        String token,

        @NotNull(message = "User ID is required")
        @Min(value = 1, message = "User ID must be a positive number")
        int idUser,

        @NotNull(message = "Trip ID is required")
        @Min(value = 1, message = "Trip ID must be a positive number")
        int idTrip,

        @NotNull(message = "Seat ID is required")
        @Min(value = 1, message = "Seat ID must be a positive number")
        int idSeat) {
}
