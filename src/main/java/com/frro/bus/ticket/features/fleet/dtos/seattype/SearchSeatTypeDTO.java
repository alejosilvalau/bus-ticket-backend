package com.frro.bus.ticket.features.fleet.dtos.seattype;

import java.math.BigDecimal;
import java.util.Optional;

public record SearchSeatTypeDTO(
        Optional<String> name,
        Optional<BigDecimal> upcharge) {
}
