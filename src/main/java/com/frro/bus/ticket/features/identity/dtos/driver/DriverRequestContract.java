package com.frro.bus.ticket.features.identity.dtos.driver;

public interface DriverRequestContract extends PersonRequestContract {
    String licenseNumber();

    String phoneNumber();
}
