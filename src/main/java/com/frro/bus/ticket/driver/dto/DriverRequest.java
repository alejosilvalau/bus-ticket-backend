package com.frro.bus.ticket.driver.dto;

public record DriverRequest(
    String firstName,
    String lastName,
    Boolean isActive,
    String licenseNumber,
    String phoneNumber
) {
}
