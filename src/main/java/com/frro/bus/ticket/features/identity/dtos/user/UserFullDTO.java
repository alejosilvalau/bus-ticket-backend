package com.frro.bus.ticket.features.identity.dtos.user;

import java.util.List;

import com.frro.bus.ticket.features.booking.dtos.TicketDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserFullDTO(
        @NotBlank int id,

        @NotBlank @NotNull String firstName,

        @NotBlank @NotNull String lastName,

        boolean isActive,

        @NotBlank @NotNull String email,

        boolean isAdmin,

        List<TicketDTO> tickets) {
}
