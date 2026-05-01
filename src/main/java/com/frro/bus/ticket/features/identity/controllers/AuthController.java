package com.frro.bus.ticket.features.identity.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.features.identity.dtos.user.ChangePasswordUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.LoginUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;
import com.frro.bus.ticket.features.identity.services.auth.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/identity/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginUserDTO userRequest) {
        UserDTO loggedInUser = authService.login(userRequest)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        return ResponseEntity.ok(loggedInUser);
    }

    @GetMapping("/logout")
    public ResponseEntity<Boolean> logout() {
        boolean result = authService.logout();
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/change-password")
    public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordUserDTO changePasswordUser) {
        boolean result = authService.changePassword(changePasswordUser);
        return ResponseEntity.ok(result);
    }
}
