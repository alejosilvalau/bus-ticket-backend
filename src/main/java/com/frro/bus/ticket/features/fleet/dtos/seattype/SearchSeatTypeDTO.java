package com.frro.bus.ticket.features.fleet.dtos.seattype;

import java.math.BigDecimal;
import java.util.Optional;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

public record SearchSeatTypeDTO(
        Optional<@Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters") String> name,
        Optional<@DecimalMin(value = "0", message = "Start upcharge must be zero or positive") BigDecimal> startUpcharge,
        Optional<@DecimalMin(value = "0", message = "End upcharge must be zero or positive") BigDecimal> endUpcharge) {
}
