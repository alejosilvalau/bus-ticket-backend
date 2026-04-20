package com.frro.bus.ticket.features.fleet.dtos;

public record SeatDTO(
    int id,
    int busId,
    int seatTypeId,
    char letter,
    int number
) {}
