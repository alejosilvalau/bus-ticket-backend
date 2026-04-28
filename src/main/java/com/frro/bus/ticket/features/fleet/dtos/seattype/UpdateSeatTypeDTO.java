package com.frro.bus.ticket.features.fleet.dtos.seattype;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateSeatTypeDTO(
        @NotBlank @NotNull int id,
        String name,
        BigDecimal upcharge) {
}
