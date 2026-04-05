package com.frro.bus.ticket.user.repository;

import com.frro.bus.ticket.person.repository.PersonRepository;
import com.frro.bus.ticket.user.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PersonRepository {

  /**
   * Find user by email
   *
   * @param email User email
   * @return Optional containing the user if found
   */
  Optional<User> findByEmail(String email);

  /**
   * Find all admin users
   *
   * @return List of admin users
   */
  List<User> findAllByIsAdminTrue();

  /**
   * Find all non-admin users
   *
   * @return List of non-admin users
   */
  List<User> findAllByIsAdminFalse();

  /**
   * Count total admin users
   *
   * @return Number of admin users
   */
  Long countByIsAdminTrue();
}

