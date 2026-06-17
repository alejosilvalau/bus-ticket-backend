package com.frro.bus.ticket.features.fleet.dtos.seat;

public record SeatDTO(
        int id,
        char letter,
        int number,
        boolean isActive) {
}
