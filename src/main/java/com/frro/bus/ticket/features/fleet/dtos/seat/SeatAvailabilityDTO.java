package com.frro.bus.ticket.features.fleet.dtos.seat;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;

public record SeatAvailabilityDTO(
        @NotBlank int id,
        @NotBlank char letter,
        @NotBlank int number,
        boolean isActive,
        @NotBlank String seatTypeName,
        @NotBlank BigDecimal seatTypeUpcharge,
        boolean isAvailable) {
}
