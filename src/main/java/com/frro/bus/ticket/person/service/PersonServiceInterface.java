package com.frro.bus.ticket.person.service;

import com.frro.bus.ticket.person.dto.PersonResponseInterface;
import java.util.List;
import java.util.Optional;

public interface PersonServiceInterface {

  /**
   * Retrieve all persons
   *
   * @return List of all persons
   */
  List<PersonResponseInterface> findAll();

  /**
   * Retrieve all active persons
   *
   * @return List of active persons
   */
  List<PersonResponseInterface> findAllActive();

  /**
   * Retrieve all inactive persons
   *
   * @return List of inactive persons
   */
  List<PersonResponseInterface> findAllInactive();

  /**
   * Find a person by ID
   *
   * @param id Person ID
   * @return Optional containing the person if found
   */
  Optional<PersonResponseInterface> findById(String id);

  /**
   * Find a person by ID if active
   *
   * @param id Person ID
   * @return Optional containing the person if found and active
   */
  Optional<PersonResponseInterface> findByIdIfActive(String id);

  /**
   * Activate a person
   *
   * @param id Person ID
   * @return Updated person
   */
  PersonResponseInterface activate(String id);

  /**
   * Deactivate a person
   *
   * @param id Person ID
   * @return Updated person
   */
  PersonResponseInterface deactivate(String id);

  /**
   * Get count of active persons
   *
   * @return Number of active persons
   */
  Long countActive();

  /**
   * Check if a person exists
   *
   * @param id Person ID
   * @return true if person exists, false otherwise
   */
  Boolean existsById(String id);
}

