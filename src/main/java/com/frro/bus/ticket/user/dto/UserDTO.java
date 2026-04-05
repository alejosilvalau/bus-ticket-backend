package com.frro.bus.ticket.user.dto;

import com.frro.bus.ticket.person.dto.PersonDTO;

public class UserDTO extends PersonDTO {

  private String email;

  private String password;

  private Boolean isAdmin;

  public UserDTO() {
  }

  public UserDTO(String id, String firstName, String lastName, Boolean isActive, String email, String password,
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
    return super.toString().substring(0, super.toString().length() - 1) + ", email=" + email + ", isAdmin=" + isAdmin
        + "]";
  }

}

