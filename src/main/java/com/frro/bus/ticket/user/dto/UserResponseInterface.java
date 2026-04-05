package com.frro.bus.ticket.user.dto;

import com.frro.bus.ticket.person.dto.PersonResponseInterface;

public interface UserResponseInterface extends PersonResponseInterface {
  String email();

  Boolean isAdmin();
}
