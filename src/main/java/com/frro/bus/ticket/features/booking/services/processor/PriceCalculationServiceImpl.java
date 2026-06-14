package com.frro.bus.ticket.features.booking.services.processor;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.frro.bus.ticket.features.fleet.entities.Seat;
import com.frro.bus.ticket.features.journey.entities.Trip;

@Service
public class PriceCalculationServiceImpl implements PriceCalculationService {

    @Override
    public BigDecimal calculateFinalPriceValue(Trip trip, Seat seat) {
        BigDecimal basePrice = trip.getBasePrice();
        BigDecimal seatUpcharge = seat.getSeatType().getUpcharge();
        return basePrice.add(seatUpcharge);
    }
}
