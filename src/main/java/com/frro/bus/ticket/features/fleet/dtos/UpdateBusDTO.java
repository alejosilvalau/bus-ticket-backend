package com.frro.bus.ticket.features.fleet.dtos;

public record UpdateBusDTO(
    int id,
    String plateNumber,
    Integer totalCapacity,
    Boolean isActive
) {}
