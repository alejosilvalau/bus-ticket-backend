package com.frro.bus.ticket.bus.dto;

public record BusResponse(
    String id,
    String plateNumber,
    Integer totalCapacity,
    Boolean isActive
) {}
