package com.frro.bus.ticket.driver.dto;

import com.frro.bus.ticket.person.dto.PersonResponseContract;

public interface DriverResponseContract extends PersonResponseContract {
    String licenseNumber();

    String phoneNumber();
}
