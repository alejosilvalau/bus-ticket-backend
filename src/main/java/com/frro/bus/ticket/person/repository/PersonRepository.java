package com.frro.bus.ticket.person.repository;

import com.frro.bus.ticket.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

  /**
   * Find all active persons
   *
   * @return List of active persons
   */
  List<Person> findAllByIsActiveTrue();

  /**
   * Find person by ID if active
   *
   * @param id Person ID
   * @return Optional containing the person if found and active
   */
  Optional<Person> findByIdAndIsActiveTrue(String id);

  /**
   * Find all inactive persons
   *
   * @return List of inactive persons
   */
  List<Person> findAllByIsActiveFalse();

  /**
   * Count total active persons
   *
   * @return Number of active persons
   */
  Long countByIsActiveTrue();
}

