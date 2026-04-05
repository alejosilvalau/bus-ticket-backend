package com.frro.bus.ticket.person.dto;

public class PersonDTO {

  private String id;

  private String firstName;

  private String lastName;

  private Boolean isActive;

  public PersonDTO() {
  }

  public PersonDTO(String id, String firstName, String lastName, Boolean isActive) {
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
    return "PersonDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", isActive=" + isActive
        + "]";
  }

}

