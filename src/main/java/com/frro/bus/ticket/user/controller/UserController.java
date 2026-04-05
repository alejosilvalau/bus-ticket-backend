package com.frro.bus.ticket.user.controller;

import com.frro.bus.ticket.user.dto.UserResponseInterface;
import com.frro.bus.ticket.user.service.UserServiceInterface;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserServiceInterface userService;

  public UserController(UserServiceInterface userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<List<?>> findAll() {
    List<?> users = userService.findAll();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/active")
  public ResponseEntity<List<?>> findAllActive() {
    List<?> users = userService.findAllActive();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/inactive")
  public ResponseEntity<List<?>> findAllInactive() {
    List<?> users = userService.findAllInactive();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable String id) {
    Optional<?> user = userService.findById(id);
    return user.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/{id}/active")
  public ResponseEntity<?> findByIdIfActive(@PathVariable String id) {
    Optional<?> user = userService.findByIdIfActive(id);
    return user.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<UserResponseInterface> findByEmail(@PathVariable String email) {
    Optional<UserResponseInterface> user = userService.findByEmail(email);
    return user.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/admins")
  public ResponseEntity<List<UserResponseInterface>> findAllAdmins() {
    List<UserResponseInterface> admins = userService.findAllAdmins();
    return ResponseEntity.ok(admins);
  }

  @GetMapping("/non-admins")
  public ResponseEntity<List<UserResponseInterface>> findAllNonAdmins() {
    List<UserResponseInterface> nonAdmins = userService.findAllNonAdmins();
    return ResponseEntity.ok(nonAdmins);
  }

  @PostMapping("/{id}/activate")
  public ResponseEntity<?> activate(@PathVariable String id) {
    try {
      return ResponseEntity.ok(userService.activate(id));
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/{id}/deactivate")
  public ResponseEntity<?> deactivate(@PathVariable String id) {
    try {
      return ResponseEntity.ok(userService.deactivate(id));
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/{id}/grant-admin")
  public ResponseEntity<UserResponseInterface> grantAdmin(@PathVariable String id) {
    try {
      UserResponseInterface user = userService.grantAdmin(id);
      return ResponseEntity.ok(user);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/{id}/revoke-admin")
  public ResponseEntity<UserResponseInterface> revokeAdmin(@PathVariable String id) {
    try {
      UserResponseInterface user = userService.revokeAdmin(id);
      return ResponseEntity.ok(user);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/count/active")
  public ResponseEntity<Long> countActive() {
    Long count = userService.countActive();
    return ResponseEntity.ok(count);
  }

  @GetMapping("/count/admins")
  public ResponseEntity<Long> countAdmins() {
    Long count = userService.countAdmins();
    return ResponseEntity.ok(count);
  }

  @GetMapping("/{id}/exists")
  public ResponseEntity<Boolean> existsById(@PathVariable String id) {
    Boolean exists = userService.existsById(id);
    return ResponseEntity.ok(exists);
  }
}
