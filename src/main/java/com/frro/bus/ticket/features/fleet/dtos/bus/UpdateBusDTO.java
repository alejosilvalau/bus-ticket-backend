package com.frro.bus.ticket.features.fleet.dtos.bus;

import java.util.Optional;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import com.frro.bus.ticket.common.config.PlateNumberDeserializer;
import com.frro.bus.ticket.common.validations.ValidPlateNumber;

import tools.jackson.databind.annotation.JsonDeserialize;

public record UpdateBusDTO(
        @NotNull @Min(1) Integer id,

        @JsonDeserialize(contentUsing = PlateNumberDeserializer.class)
        Optional<@ValidPlateNumber String> plateNumber,

        Optional<@Min(1) Integer> totalCapacity,

        Optional<Boolean> isActive) {
}
