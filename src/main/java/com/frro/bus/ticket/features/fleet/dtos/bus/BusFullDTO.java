package com.frro.bus.ticket.features.fleet.dtos.bus;

import java.util.List;

import com.frro.bus.ticket.features.fleet.dtos.seat.SeatDTO;
import com.frro.bus.ticket.features.journey.dtos.trip.TripDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BusFullDTO(
        @NotBlank int id,
        @NotBlank @NotNull String plateNumber,
        @NotBlank int totalCapacity,
        boolean isActive,
        List<TripDTO> trips,
        List<SeatDTO> seats) {
}
