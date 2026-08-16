package com.frro.bus.ticket.features.fleet.dtos.seattype;

import java.math.BigDecimal;
import java.util.Optional;

import com.frro.bus.ticket.common.validations.ValidName;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateSeatTypeDTO(
        @NotNull @Min(1) Integer id,

        Optional<@Size(min = 1, max = 50) @ValidName String> name,

        Optional<@DecimalMin("0") BigDecimal> upcharge) {
}
