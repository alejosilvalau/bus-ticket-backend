package com.frro.bus.ticket.person.repository;

import com.frro.bus.ticket.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

  List<Person> findAllByIsActiveTrue();

  Optional<Person> findByIdAndIsActiveTrue(int id);

  List<Person> findAllByIsActiveFalse();

  Long countByIsActiveTrue();
}
