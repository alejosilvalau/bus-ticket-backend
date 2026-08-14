package com.frro.bus.ticket.features.fleet.dtos.bus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.frro.bus.ticket.common.config.PlateNumberDeserializer;
import com.frro.bus.ticket.common.validations.ValidPlateNumber;

import tools.jackson.databind.annotation.JsonDeserialize;

public record CreateBusDTO(
        @JsonDeserialize(using = PlateNumberDeserializer.class)
        @NotBlank @ValidPlateNumber String plateNumber,

        @NotNull @Min(1) Integer totalCapacity,

        @NotNull Boolean isActive) {
}
