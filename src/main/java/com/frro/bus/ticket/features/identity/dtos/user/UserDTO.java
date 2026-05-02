package com.frro.bus.ticket.features.identity.dtos.user;

// import java.util.List;

// import com.frro.bus.ticket.features.booking.dtos.TicketDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record UserDTO(
        @NotBlank @NotNull int id,

        @NotBlank @NotNull String firstName,

        @NotBlank @NotNull String lastName,

        @NotBlank @NotNull Boolean isActive,

        @NotBlank @NotNull String email,

        @NotBlank @NotNull Boolean isAdmin

// @NotBlank @NotNull List<TicketDTO> tickets
) {
}
