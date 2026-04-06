package com.frro.bus.ticket.person.service;

import com.frro.bus.ticket.person.dto.PersonResponseInterface;
import java.util.List;
import java.util.Optional;

public interface PersonServiceInterface {

  List<PersonResponseInterface> findAll();

  List<PersonResponseInterface> findAllActive();

  List<PersonResponseInterface> findAllInactive();

  Optional<PersonResponseInterface> findById(int id);

  Optional<PersonResponseInterface> findByIdIfActive(int id);

  PersonResponseInterface activate(int id);

  PersonResponseInterface deactivate(int id);

  Long countActive();

  Boolean existsById(int id);
}
