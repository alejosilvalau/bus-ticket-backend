package com.frro.bus.ticket.features.identity.dtos.driver;

public record DriverRequest(
        String firstName,
        String lastName,
        Boolean isActive,
        String licenseNumber,
        String phoneNumber) implements DriverRequestContract {
}
