package com.frro.bus.ticket.features.fleet.dtos.bus;

public record BusDTO(
        int id,
        String plateNumber,
        int totalCapacity,
        boolean isActive) {
}
