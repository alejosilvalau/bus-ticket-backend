package com.frro.bus.ticket.features.booking.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record GetTicketFinalPriceDTO(
        @NotNull @Min(1) Integer tripId,

        @NotNull @Min(1) Integer seatId) {
}
