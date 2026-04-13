package com.frro.bus.ticket.bus.dto;

public record BusRequest(
    String plateNumber,
    Integer totalCapacity,
    Boolean isActive
) {}
