package com.frro.bus.ticket.features.fleet.dtos.seat;

import java.math.BigDecimal;

public record SeatAvailabilityDTO(
        int id,
        char letter,
        int number,
        boolean isActive,
        String seatTypeName,
        BigDecimal seatTypeUpcharge,
        boolean isAvailable) {
}
