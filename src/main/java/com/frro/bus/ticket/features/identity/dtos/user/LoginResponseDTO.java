package com.frro.bus.ticket.features.identity.dtos.user;

public record LoginResponseDTO(
        String token,
        UserDTO user) {
}
