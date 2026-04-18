package com.frro.bus.ticket.features.fleet.dtos;

public record CreateBusDTO(
    String plateNumber,
    Integer totalCapacity,
    Boolean isActive
) {}
