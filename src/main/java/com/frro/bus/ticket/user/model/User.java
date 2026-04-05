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
public class User extends Person {

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private Boolean isAdmin = false;

  public User(String firstName, String lastName, Boolean isActive, String email, String password,
      Boolean isAdmin) {
    super(firstName, lastName, isActive);
    this.email = email;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  @Override
  public String toString() {
    return super.toString() + ", email=" + email + ", isAdmin=" + isAdmin + "]";
  }

}
