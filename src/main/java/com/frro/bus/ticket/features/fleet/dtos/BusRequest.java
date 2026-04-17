package com.frro.bus.ticket.features.fleet.dtos;

public record BusRequest(
        String plateNumber,
        Integer totalCapacity,
        Boolean isActive) {
}
