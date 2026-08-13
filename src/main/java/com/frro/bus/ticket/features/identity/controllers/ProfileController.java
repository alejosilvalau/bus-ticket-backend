package com.frro.bus.ticket.features.identity.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.common.dto.ApiResponse;
import com.frro.bus.ticket.common.security.endpointhelpers.AuthenticatedEndpoint;
import com.frro.bus.ticket.features.identity.dtos.user.UpdateUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;
import com.frro.bus.ticket.features.identity.services.profile.ProfileService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/identity/profile")
@AuthenticatedEndpoint
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PatchMapping
    public ResponseEntity<ApiResponse<UserDTO>> update(@Valid @RequestBody UpdateUserDTO userRequest) {
        UserDTO user = profileService.update(userRequest);
        return ResponseEntity.ok(ApiResponse.success("Profile updated successfully", user));
    }

    @PatchMapping("/logical-delete")
    public ResponseEntity<ApiResponse<UserDTO>> logicalDelete() {
        UserDTO user = profileService.logicalDelete();
        return ResponseEntity.ok(ApiResponse.success("User logically deleted successfully", user));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<UserDTO>> delete() {
        UserDTO user = profileService.delete();
        return ResponseEntity.ok(ApiResponse.success("User deleted successfully", user));
    }
}
