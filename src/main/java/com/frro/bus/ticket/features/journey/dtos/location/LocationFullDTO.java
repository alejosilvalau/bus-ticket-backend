package com.frro.bus.ticket.features.journey.dtos.location;

import java.util.List;

import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LocationFullDTO(
        @NotBlank int id,
        @NotBlank @NotNull String cityName,
        @NotBlank @NotNull String state,
        @NotBlank @NotNull String postalCode,
        List<TripDTO> tripsOrigin,
        List<TripDTO> tripsDestination) {
}
