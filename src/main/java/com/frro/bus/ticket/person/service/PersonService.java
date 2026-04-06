package com.frro.bus.ticket.person.service;

import com.frro.bus.ticket.person.dto.PersonResponseInterface;
import com.frro.bus.ticket.person.mapper.PersonMapper;
import com.frro.bus.ticket.person.model.Person;
import com.frro.bus.ticket.person.repository.PersonRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService implements PersonServiceInterface {

  private final PersonRepository personRepository;
  private final PersonMapper personMapper;

  @Override
  public List<PersonResponseInterface> findAll() {
    return personRepository.findAll().stream()
        .map(personMapper::toPersonResponse)
        .map(p -> (PersonResponseInterface) p)
        .toList();
  }

  @Override
  public List<PersonResponseInterface> findAllActive() {
    return personRepository.findAllByIsActiveTrue().stream()
        .map(personMapper::toPersonResponse)
        .map(p -> (PersonResponseInterface) p)
        .toList();
  }

  @Override
  public List<PersonResponseInterface> findAllInactive() {
    return personRepository.findAllByIsActiveFalse().stream()
        .map(personMapper::toPersonResponse)
        .map(p -> (PersonResponseInterface) p)
        .toList();
  }

  @Override
  public Optional<PersonResponseInterface> findById(int id) {
    return personRepository.findById(id)
        .map(personMapper::toPersonResponse)
        .map(p -> (PersonResponseInterface) p);
  }

  @Override
  public Optional<PersonResponseInterface> findByIdIfActive(int id) {
    return personRepository.findByIdAndIsActiveTrue(id)
        .map(personMapper::toPersonResponse)
        .map(p -> (PersonResponseInterface) p);
  }

  @Override
  public PersonResponseInterface activate(int id) {
    Person person = personRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Person not found with id: " + id));
    person.setIsActive(true);
    personRepository.save(person);
    return personMapper.toPersonResponse(person);
  }

  @Override
  public PersonResponseInterface deactivate(int id) {
    Person person = personRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Person not found with id: " + id));
    person.setIsActive(false);
    personRepository.save(person);
    return personMapper.toPersonResponse(person);
  }

  @Override
  public Long countActive() {
    return personRepository.countByIsActiveTrue();
  }

  @Override
  public Boolean existsById(String id) {
    return personRepository.existsById(id);
  }
}
