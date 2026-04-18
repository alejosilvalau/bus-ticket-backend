package com.frro.bus.ticket.features.identity.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.features.identity.dtos.user.CreateUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/register")
@RequiredArgsConstructor
public class UserRegisterController {
    // private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody CreateUserDTO createUser) {
        UserDTO savedUser = userService.create(createUser);
        return ResponseEntity.ok(savedUser);
    }
}
