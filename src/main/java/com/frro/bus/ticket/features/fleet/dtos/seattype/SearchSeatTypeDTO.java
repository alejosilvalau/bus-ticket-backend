package com.frro.bus.ticket.features.fleet.dtos.seattype;

import java.math.BigDecimal;
import java.util.Optional;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

public record SearchSeatTypeDTO(
        Optional<@Size(min = 1, max = 50) String> name,
        Optional<@DecimalMin("0") BigDecimal> startUpcharge,
        Optional<@DecimalMin("0") BigDecimal> endUpcharge) {
}
