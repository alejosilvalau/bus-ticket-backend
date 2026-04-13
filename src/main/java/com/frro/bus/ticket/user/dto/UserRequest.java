package com.frro.bus.ticket.user.dto;

public record UserRequest(
        String firstName,
        String lastName,
        String email
) implements UserRequestInterface {
}
