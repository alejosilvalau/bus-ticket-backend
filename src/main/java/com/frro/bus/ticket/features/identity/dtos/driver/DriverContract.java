package com.frro.bus.ticket.features.identity.dtos.driver;

public interface DriverDTOContract {
    String getId();

    String getFirstName();

    String getLastName();

    Boolean getIsActive();

    String getLicenseNumber();

    String getPhoneNumber();
}
