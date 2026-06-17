package com.frro.bus.ticket.features.identity.dtos.user;

public record UserDTO(
        int id,

        String firstName,

        String lastName,

        boolean isActive,

        String email,

        boolean isAdmin) {
}
