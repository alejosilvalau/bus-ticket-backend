package com.frro.bus.ticket.features.fleet.dtos.seattype;

import java.math.BigDecimal;
import java.util.Optional;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateSeatTypeDTO(
        @NotNull(message = "Seat type ID is required") @Min(value = 1, message = "Seat type ID must be a positive number") Integer id,

        Optional<@Size(min = 1, max = 50, message = "Seat type name must be between 1 and 50 characters") String> name,

        Optional<@DecimalMin(value = "0", message = "Upcharge must be zero or positive") BigDecimal> upcharge) {
}
