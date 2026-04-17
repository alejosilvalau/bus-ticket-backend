package com.frro.bus.ticket.features.fleet.dtos;

public record BusResponse(
        String id,
        String plateNumber,
        Integer totalCapacity,
        Boolean isActive) {
}
