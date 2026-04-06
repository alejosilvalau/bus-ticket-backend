package com.frro.bus.ticket.person.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorColumn(name = "is_user", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private Boolean isActive = true;

  public Person(String firstName, String lastName, Boolean isActive) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.isActive = isActive;
  }

  @Override
  public String toString() {
    return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName;
  }
}
