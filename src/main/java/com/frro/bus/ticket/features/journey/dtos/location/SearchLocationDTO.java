package com.frro.bus.ticket.features.journey.dtos.location;

import java.util.Optional;

public record SearchLocationDTO(
        Optional<String> cityName,
        Optional<String> state) {
}
