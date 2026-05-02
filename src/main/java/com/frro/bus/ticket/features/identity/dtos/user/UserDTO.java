package com.frro.bus.ticket.features.identity.dtos.user;

// import java.util.List;

// import com.frro.bus.ticket.features.booking.dtos.TicketDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record UserDTO(
        @NotBlank int id,

        @NotBlank @NotNull String firstName,

        @NotBlank @NotNull String lastName,

        @NotBlank boolean isActive,

        @NotBlank @NotNull String email,

        @NotBlank boolean isAdmin

// @NotBlank @NotNull List<TicketDTO> tickets
) {
}
