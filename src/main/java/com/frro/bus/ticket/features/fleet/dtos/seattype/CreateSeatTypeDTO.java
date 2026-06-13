package com.frro.bus.ticket.features.fleet.dtos.seattype;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateSeatTypeDTO(
        @NotBlank(message = "Seat type name is required")
        @Size(min = 1, max = 50, message = "Seat type name must be between 1 and 50 characters")
        String name,

        @NotNull(message = "Upcharge is required")
        @DecimalMin(value = "0", message = "Upcharge must be zero or positive")
        BigDecimal upcharge) {
}
