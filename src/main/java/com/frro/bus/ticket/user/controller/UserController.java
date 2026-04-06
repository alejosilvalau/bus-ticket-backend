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
  public ResponseEntity<List<UserResponseInterface>> findAll() {
    List<UserResponseInterface> users = userService.findAll();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseInterface> findById(@PathVariable int id) {
    Optional<UserResponseInterface> user = userService.findById(id);
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

  @GetMapping("/count/admins")
  public ResponseEntity<Long> countAdmins() {
    Long count = userService.countAdmins();
    return ResponseEntity.ok(count);
  }
}
