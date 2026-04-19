package com.frro.bus.ticket.features.fleet.dtos;

public record UpdateSeatDTO(
    int id,
    Integer busId,
    Integer seatTypeId,
    Character letter,
    Integer number
) {}
