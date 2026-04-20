package com.frro.bus.ticket.features.fleet.dtos;

public record CreateSeatDTO(
    int busId,
    int seatTypeId,
    char letter,
    int number
) {}
