package com.frro.bus.ticket.features.identity.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/identity/profile")
@AuthenticatedEndpoint
@RequiredArgsConstructor
public class ProfileController {

    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    private final ProfileService profileService;

    @PatchMapping
    public ResponseEntity<ApiResponse<UserDTO>> update(@Valid @RequestBody UpdateUserDTO userRequest) {
        try {
            return profileService.update(userRequest)
                    .map(user -> ResponseEntity.ok(ApiResponse.success("Profile updated successfully", user)))
                    .orElseGet(() -> ResponseEntity.status(404)
                            .body(ApiResponse.error("User not found with id: " + userRequest.id())));
        } catch (Exception e) {
            log.error("Failed to update profile with id: {}", userRequest.id(), e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("Failed to update profile. Please try again later."));
        }
    }

    @PatchMapping("/logical-delete")
    public ResponseEntity<ApiResponse<UserDTO>> logicalDelete() {
        try {
            return profileService.logicalDelete()
                    .map(user -> ResponseEntity.ok(ApiResponse.success("User logically deleted successfully", user)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("User not found")));
        } catch (Exception e) {
            log.error("Failed to logically delete user", e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("Failed to delete user. Please try again later."));
        }
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<UserDTO>> delete() {
        try {
            return profileService.delete()
                    .map(user -> ResponseEntity.ok(ApiResponse.success("User deleted successfully", user)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("User not found")));
        } catch (Exception e) {
            log.error("Failed to delete user", e);
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error("Failed to delete user. Please try again later."));
        }
    }
}
