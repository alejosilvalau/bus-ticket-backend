package com.frro.bus.ticket.features.fleet.dtos.seattype;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateSeatTypeDTO(
        @NotBlank @Size(min = 1, max = 50) String name,

        @NotNull @DecimalMin("0") BigDecimal upcharge) {
}
