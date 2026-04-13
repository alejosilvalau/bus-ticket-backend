package com.frro.bus.ticket.user.dto;

public record UserRequest(
        String firstName,
        String lastName,
        Boolean isActive,
        String email,
        String password) implements UserRequestContract {
}
