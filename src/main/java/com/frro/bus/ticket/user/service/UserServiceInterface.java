package com.frro.bus.ticket.user.service;

import com.frro.bus.ticket.user.dto.UserResponseInterface;
import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
  List<UserResponseInterface> findAll();

  Optional<UserResponseInterface> findById(int id);

  Optional<UserResponseInterface> findByEmail(String email);

  List<UserResponseInterface> findAllAdmins();

  List<UserResponseInterface> findAllNonAdmins();

  UserResponseInterface grantAdmin(int id);

  UserResponseInterface revokeAdmin(int id);

  Long countAdmins();
}
