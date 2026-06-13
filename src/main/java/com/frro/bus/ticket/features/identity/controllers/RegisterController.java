package com.frro.bus.ticket.features.identity.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.common.dto.ApiResponse;
import com.frro.bus.ticket.features.identity.dtos.user.CreateUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;
import com.frro.bus.ticket.features.identity.services.register.RegisterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/identity/register")
@RequiredArgsConstructor
public class RegisterController {

    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserDTO>> create(@RequestBody CreateUserDTO createUser) {
        try {
            UserDTO savedUser = registerService.create(createUser);
            return ResponseEntity.ok(ApiResponse.success("User registered successfully", savedUser));
        } catch (Exception e) {
            log.error("Registration failed", e);
            return ResponseEntity.internalServerError().body(ApiResponse.error("Registration failed. Please try again later."));
        }
    }
}
