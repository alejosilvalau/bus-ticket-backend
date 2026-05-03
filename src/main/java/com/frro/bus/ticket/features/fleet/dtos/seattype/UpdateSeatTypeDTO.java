package com.frro.bus.ticket.features.fleet.dtos.seattype;

import java.math.BigDecimal;
import java.util.Optional;

import jakarta.validation.constraints.NotBlank;

public record UpdateSeatTypeDTO(
        @NotBlank int id,
        Optional<String> name,
        Optional<BigDecimal> upcharge) {
}
