package com.frro.bus.ticket.features.identity.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.common.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse<UserDTO>> login(@RequestBody LoginUserDTO userRequest) {
        try {
            return authService.login(userRequest)
                    .map(user -> ResponseEntity.ok(ApiResponse.success("Login successful", user)))
                    .orElseGet(() -> ResponseEntity.status(401).body(ApiResponse.error("Invalid email or password")));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Login failed: " + e.getMessage()));
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<ApiResponse<Boolean>> logout() {
        try {
            boolean result = authService.logout();
            return ResponseEntity.ok(ApiResponse.success("Logout successful", result));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Logout failed: " + e.getMessage()));
        }
    }

    @PatchMapping("/change-password")
    public ResponseEntity<ApiResponse<Boolean>> changePassword(@RequestBody ChangePasswordUserDTO changePasswordUser) {
        try {
            boolean result = authService.changePassword(changePasswordUser);
            if (result) {
                return ResponseEntity.ok(ApiResponse.success("Password changed successfully", result));
            } else {
                return ResponseEntity.badRequest().body(ApiResponse.error("Failed to change password: invalid credentials"));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Password change failed: " + e.getMessage()));
        }
    }
}
