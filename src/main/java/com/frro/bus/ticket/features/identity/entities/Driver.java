package com.frro.bus.ticket.driver.model;

import com.frro.bus.ticket.person.model.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("0") // 0 for False (Driver)
public class Driver extends Person {
    @Column(nullable = true, unique = true)
    private String licenseNumber;

    @Column(nullable = true)
    private String phoneNumber;

    public Driver(String firstName, String lastName, Boolean isActive, String licenseNumber, String phoneNumber) {
        super(firstName, lastName, isActive);
        this.licenseNumber = licenseNumber;
        this.phoneNumber = phoneNumber;
    }
}
