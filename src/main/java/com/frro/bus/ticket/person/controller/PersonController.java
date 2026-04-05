package com.frro.bus.ticket.person.controller;

import com.frro.bus.ticket.person.dto.PersonResponseInterface;
import com.frro.bus.ticket.person.service.PersonServiceInterface;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

  private final PersonServiceInterface personService;

  public PersonController(PersonServiceInterface personService) {
    this.personService = personService;
  }

  @GetMapping
  public ResponseEntity<List<PersonResponseInterface>> findAll() {
    List<PersonResponseInterface> persons = personService.findAll();
    return ResponseEntity.ok(persons);
  }

  @GetMapping("/active")
  public ResponseEntity<List<PersonResponseInterface>> findAllActive() {
    List<PersonResponseInterface> persons = personService.findAllActive();
    return ResponseEntity.ok(persons);
  }

  @GetMapping("/inactive")
  public ResponseEntity<List<PersonResponseInterface>> findAllInactive() {
    List<PersonResponseInterface> persons = personService.findAllInactive();
    return ResponseEntity.ok(persons);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PersonResponseInterface> findById(@PathVariable String id) {
    Optional<PersonResponseInterface> person = personService.findById(id);
    return person.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/{id}/active")
  public ResponseEntity<PersonResponseInterface> findByIdIfActive(@PathVariable String id) {
    Optional<PersonResponseInterface> person = personService.findByIdIfActive(id);
    return person.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/{id}/activate")
  public ResponseEntity<PersonResponseInterface> activate(@PathVariable String id) {
    try {
      PersonResponseInterface person = personService.activate(id);
      return ResponseEntity.ok(person);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/{id}/deactivate")
  public ResponseEntity<PersonResponseInterface> deactivate(@PathVariable String id) {
    try {
      PersonResponseInterface person = personService.deactivate(id);
      return ResponseEntity.ok(person);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/count/active")
  public ResponseEntity<Long> countActive() {
    Long count = personService.countActive();
    return ResponseEntity.ok(count);
  }

  @GetMapping("/{id}/exists")
  public ResponseEntity<Boolean> existsById(@PathVariable String id) {
    Boolean exists = personService.existsById(id);
    return ResponseEntity.ok(exists);
  }
}
