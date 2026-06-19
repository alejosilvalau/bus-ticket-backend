package com.frro.bus.ticket.features.identity.dtos.driver;

public record DriverDTO(
        int id,
        String firstName,
        String lastName,
        boolean isActive,
        String licenseNumber,
        String phoneNumber) {
}
