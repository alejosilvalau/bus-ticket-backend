package com.frro.bus.ticket.driver.dto;

import com.frro.bus.ticket.person.dto.PersonRequestInterface;

public interface DriverRequestInterface extends PersonRequestInterface {
    String licenseNumber();

    String phoneNumber();
}
