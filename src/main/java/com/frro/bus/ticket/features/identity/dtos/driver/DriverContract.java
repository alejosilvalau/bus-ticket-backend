package com.frro.bus.ticket.features.identity.dtos.driver;

import com.frro.bus.ticket.features.identity.dtos.person.PersonContract;

public interface DriverContract extends PersonContract {
    String licenseNumber();

    String phoneNumber();
}
