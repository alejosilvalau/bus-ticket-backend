package com.frro.bus.ticket.features.booking.dtos;

import java.time.ZonedDateTime;

public record TokenDTO(
        String busPlateNumber,
        String driverName,
        String originCityName,
        String destinationCityName,
        ZonedDateTime tripDepartureDate,
        ZonedDateTime tripArrivalDate,
        char seatLetter,
        int seatNumber,
        String seatTypeName,
        ZonedDateTime bookingTime) {
}
