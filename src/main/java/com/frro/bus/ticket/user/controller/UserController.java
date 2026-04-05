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

  /**
   * Get all users
   *
   * @return List of all users
   */
  @GetMapping
  public ResponseEntity<List<?>> findAll() {
    List<?> users = userService.findAll();
    return ResponseEntity.ok(users);
  }

  /**
   * Get all active users
   *
   * @return List of active users
   */
  @GetMapping("/active")
  public ResponseEntity<List<?>> findAllActive() {
    List<?> users = userService.findAllActive();
    return ResponseEntity.ok(users);
  }

  /**
   * Get all inactive users
   *
   * @return List of inactive users
   */
  @GetMapping("/inactive")
  public ResponseEntity<List<?>> findAllInactive() {
    List<?> users = userService.findAllInactive();
    return ResponseEntity.ok(users);
  }

  /**
   * Get a user by ID
   *
   * @param id User ID
   * @return User if found, 404 otherwise
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable String id) {
    Optional<?> user = userService.findById(id);
    return user.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Get a user by ID if active
   *
   * @param id User ID
   * @return User if found and active, 404 otherwise
   */
  @GetMapping("/{id}/active")
  public ResponseEntity<?> findByIdIfActive(@PathVariable String id) {
    Optional<?> user = userService.findByIdIfActive(id);
    return user.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Find a user by email
   *
   * @param email User email
   * @return User if found, 404 otherwise
   */
  @GetMapping("/email/{email}")
  public ResponseEntity<UserResponseInterface> findByEmail(@PathVariable String email) {
    Optional<UserResponseInterface> user = userService.findByEmail(email);
    return user.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Get all admin users
   *
   * @return List of admin users
   */
  @GetMapping("/admins")
  public ResponseEntity<List<UserResponseInterface>> findAllAdmins() {
    List<UserResponseInterface> admins = userService.findAllAdmins();
    return ResponseEntity.ok(admins);
  }

  /**
   * Get all non-admin users
   *
   * @return List of non-admin users
   */
  @GetMapping("/non-admins")
  public ResponseEntity<List<UserResponseInterface>> findAllNonAdmins() {
    List<UserResponseInterface> nonAdmins = userService.findAllNonAdmins();
    return ResponseEntity.ok(nonAdmins);
  }

  /**
   * Activate a user
   *
   * @param id User ID
   * @return Updated user if found, 404 otherwise
   */
  @PostMapping("/{id}/activate")
  public ResponseEntity<?> activate(@PathVariable String id) {
    try {
      return ResponseEntity.ok(userService.activate(id));
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Deactivate a user
   *
   * @param id User ID
   * @return Updated user if found, 404 otherwise
   */
  @PostMapping("/{id}/deactivate")
  public ResponseEntity<?> deactivate(@PathVariable String id) {
    try {
      return ResponseEntity.ok(userService.deactivate(id));
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Grant admin privileges to a user
   *
   * @param id User ID
   * @return Updated user if found, 404 otherwise
   */
  @PostMapping("/{id}/grant-admin")
  public ResponseEntity<UserResponseInterface> grantAdmin(@PathVariable String id) {
    try {
      UserResponseInterface user = userService.grantAdmin(id);
      return ResponseEntity.ok(user);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Revoke admin privileges from a user
   *
   * @param id User ID
   * @return Updated user if found, 404 otherwise
   */
  @PostMapping("/{id}/revoke-admin")
  public ResponseEntity<UserResponseInterface> revokeAdmin(@PathVariable String id) {
    try {
      UserResponseInterface user = userService.revokeAdmin(id);
      return ResponseEntity.ok(user);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Get count of active users
   *
   * @return Number of active users
   */
  @GetMapping("/count/active")
  public ResponseEntity<Long> countActive() {
    Long count = userService.countActive();
    return ResponseEntity.ok(count);
  }

  /**
   * Get count of admin users
   *
   * @return Number of admin users
   */
  @GetMapping("/count/admins")
  public ResponseEntity<Long> countAdmins() {
    Long count = userService.countAdmins();
    return ResponseEntity.ok(count);
  }

  /**
   * Check if a user exists
   *
   * @param id User ID
   * @return Boolean indicating if user exists
   */
  @GetMapping("/{id}/exists")
  public ResponseEntity<Boolean> existsById(@PathVariable String id) {
    Boolean exists = userService.existsById(id);
    return ResponseEntity.ok(exists);
  }
}

