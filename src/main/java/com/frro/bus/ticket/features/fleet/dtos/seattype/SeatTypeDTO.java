package com.frro.bus.ticket.features.fleet.dtos.seattype;

import java.math.BigDecimal;

public record SeatTypeDTO(
        int id,
        String name,
        BigDecimal upcharge) {
}
