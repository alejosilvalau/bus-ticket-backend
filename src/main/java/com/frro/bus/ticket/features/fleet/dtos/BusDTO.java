package com.frro.bus.ticket.features.fleet.dtos;

public record BusDTO(
    int id,
    String plateNumber,
    Integer totalCapacity,
    Boolean isActive
) {}
