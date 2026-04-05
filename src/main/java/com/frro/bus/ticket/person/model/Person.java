package com.frro.bus.ticket.person.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "person")
public abstract class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private Boolean isActive = true;

  public Person() {
  }

  public Person(String id, String firstName, String lastName, Boolean isActive) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.isActive = isActive;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  @Override
  public String toString() {
    return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName;
  }
}
