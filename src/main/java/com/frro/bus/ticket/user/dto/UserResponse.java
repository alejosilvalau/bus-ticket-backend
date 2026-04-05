package com.frro.bus.ticket.user.dto;

public record UserResponse(
    String id,
    String firstName,
    String lastName,
    Boolean isActive,
    String email,
    Boolean isAdmin
) {
}

