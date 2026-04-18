package com.frro.bus.ticket.features.identity.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.features.identity.dtos.user.UpdateUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    @PatchMapping("/update")
    public ResponseEntity<UserDTO> update(@RequestBody UpdateUserDTO userRequest) {
        Optional<UserDTO> updatedUser = userService.update(userRequest);
        return updatedUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<UserDTO> delete() {
        Optional<UserDTO> deletedUser = userService.delete();
        return deletedUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/logical-delete")
    public ResponseEntity<UserDTO> logicalDelete() {
        Optional<UserDTO> updatedUser = userService.logicalDelete();
        return updatedUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
