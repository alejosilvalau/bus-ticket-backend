package com.frro.bus.ticket.features.booking.services.processor;

import java.math.BigDecimal;

import com.frro.bus.ticket.features.fleet.entities.Seat;
import com.frro.bus.ticket.features.journey.entities.Trip;

public interface PriceCalculationService {
    BigDecimal calculateFinalPriceValue(Trip trip, Seat seat);
}
