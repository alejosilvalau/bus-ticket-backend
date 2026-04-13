package com.frro.bus.ticket.user.controller;

import com.frro.bus.ticket.user.dto.UserRequest;
import com.frro.bus.ticket.user.dto.UserResponse;
import com.frro.bus.ticket.user.service.UserServiceInterface;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserResponse> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable int id) {
        Optional<UserResponse> user = userService.findById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
        UserResponse savedUser = userService.create(userRequest);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable int id, @RequestBody UserRequest userRequest) {
        Optional<UserResponse> updatedUser = userService.update(id, userRequest);
        return updatedUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> delete(@PathVariable int id) {
        Optional<UserResponse> deletedUser = userService.delete(id);
        return deletedUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
