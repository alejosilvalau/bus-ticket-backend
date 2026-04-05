package com.frro.bus.ticket.user.model;

import com.frro.bus.ticket.person.model.Person;

import jakarta.persistence.*;

@Entity
public class User extends Person {

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private Boolean isAdmin = false;

  public User() {
  }

  public User(String id, String firstName, String lastName, Boolean isActive, String email, String password,
      Boolean isAdmin) {
    super(id, firstName, lastName, isActive);
    this.email = email;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(Boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  @Override
  public String toString() {
    return super.toString() + ", email=" + email + ", isAdmin=" + isAdmin + "]";
  }

}
