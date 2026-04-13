package com.frro.bus.ticket.driver.dto;

import com.frro.bus.ticket.person.dto.PersonResponseInterface;

public interface DriverResponseInterface extends PersonResponseInterface {
    String licenseNumber();

    String phoneNumber();
}
