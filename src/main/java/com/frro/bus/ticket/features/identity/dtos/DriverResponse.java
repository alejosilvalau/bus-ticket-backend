package com.frro.bus.ticket.driver.dto;

public record DriverResponse(
        String id,
        String firstName,
        String lastName,
        Boolean isActive,
        String licenseNumber,
        String phoneNumber) implements DriverResponseContract {
}
