package com.frro.bus.ticket.person.service;

import com.frro.bus.ticket.person.dto.PersonResponseInterface;
import java.util.List;
import java.util.Optional;

public interface PersonServiceInterface {

  List<PersonResponseInterface> findAll();

  List<PersonResponseInterface> findAllActive();

  List<PersonResponseInterface> findAllInactive();

  Optional<PersonResponseInterface> findById(String id);

  Optional<PersonResponseInterface> findByIdIfActive(String id);

  PersonResponseInterface activate(String id);

  PersonResponseInterface deactivate(String id);

  Long countActive();

  Boolean existsById(String id);
}
