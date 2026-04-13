package com.frro.bus.ticket.driver.dto;

import com.frro.bus.ticket.person.dto.PersonRequestContract;

public interface DriverRequestContract extends PersonRequestContract {
    String licenseNumber();

    String phoneNumber();
}
