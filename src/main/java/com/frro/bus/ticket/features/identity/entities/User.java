package com.frro.bus.ticket.user.model;

import com.frro.bus.ticket.person.model.Person;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("1") // 1 for True (User)
public class User extends Person {
    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = true)
    private String password;

    @Column(nullable = true)
    private Boolean isAdmin = false;

    public User(String firstName, String lastName, Boolean isActive, String email, String password,
            Boolean isAdmin) {
        super(firstName, lastName, isActive);
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }
}
