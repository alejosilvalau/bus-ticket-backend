package com.frro.bus.ticket.user.service;

import com.frro.bus.ticket.person.service.PersonServiceInterface;
import com.frro.bus.ticket.user.dto.UserResponseInterface;
import java.util.List;
import java.util.Optional;

public interface UserServiceInterface extends PersonServiceInterface {

  /**
   * Find a user by email
   *
   * @param email User email
   * @return Optional containing the user if found
   */
  Optional<UserResponseInterface> findByEmail(String email);

  /**
   * Find all admin users
   *
   * @return List of admin users
   */
  List<UserResponseInterface> findAllAdmins();

  /**
   * Find all non-admin users
   *
   * @return List of non-admin users
   */
  List<UserResponseInterface> findAllNonAdmins();

  /**
   * Grant admin privileges to a user
   *
   * @param id User ID
   * @return Updated user
   */
  UserResponseInterface grantAdmin(String id);

  /**
   * Revoke admin privileges from a user
   *
   * @param id User ID
   * @return Updated user
   */
  UserResponseInterface revokeAdmin(String id);

  /**
   * Get count of admin users
   *
   * @return Number of admin users
   */
  Long countAdmins();
}

