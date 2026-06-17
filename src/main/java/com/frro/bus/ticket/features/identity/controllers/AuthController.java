package com.frro.bus.ticket.features.identity.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.common.dto.ApiResponse;
import com.frro.bus.ticket.common.security.endpointhelpers.AuthenticatedEndpoint;
import com.frro.bus.ticket.common.security.endpointhelpers.PublicEndpoint;
import com.frro.bus.ticket.features.identity.dtos.user.ChangePasswordUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.LoginResponseDTO;
import com.frro.bus.ticket.features.identity.dtos.user.LoginUserDTO;
import com.frro.bus.ticket.features.identity.services.auth.AuthService;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/identity/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PublicEndpoint
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@Valid @RequestBody LoginUserDTO userRequest) {
        LoginResponseDTO response = authService.login(userRequest);
        return ResponseEntity.ok(ApiResponse.success("Login successful", response));
    }

    @AuthenticatedEndpoint
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/logout")
    public ResponseEntity<ApiResponse<Boolean>> logout() {
        boolean result = authService.logout();
        return ResponseEntity.ok(ApiResponse.success("Logout successful", result));
    }

    @PublicEndpoint
    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping("/change-password")
    public ResponseEntity<ApiResponse<Boolean>> changePassword(
            @Valid @RequestBody ChangePasswordUserDTO changePasswordUser) {
        boolean result = authService.changePassword(changePasswordUser);
        return ResponseEntity.ok(ApiResponse.success("Password changed successfully", result));
    }
}
