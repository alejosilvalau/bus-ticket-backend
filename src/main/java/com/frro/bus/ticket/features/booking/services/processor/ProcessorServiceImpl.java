package com.frro.bus.ticket.features.booking.services.processor;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.frro.bus.ticket.common.exceptions.BusinessException;
import com.frro.bus.ticket.common.exceptions.DuplicateResourceException;
import com.frro.bus.ticket.common.exceptions.ResourceNotFoundException;
import com.frro.bus.ticket.features.booking.dtos.CreateTicketDTO;
import com.frro.bus.ticket.features.booking.dtos.GetTicketFinalPriceDTO;
import com.frro.bus.ticket.features.booking.dtos.TicketFullDTO;
import com.frro.bus.ticket.features.booking.dtos.UpdateTicketDTO;
import com.frro.bus.ticket.features.booking.entities.Ticket;
import com.frro.bus.ticket.features.booking.mappers.TicketMapper;
import com.frro.bus.ticket.features.booking.repositories.TicketRepository;
import com.frro.bus.ticket.features.fleet.entities.Seat;
import com.frro.bus.ticket.features.fleet.repositories.SeatRepository;
import com.frro.bus.ticket.features.identity.entities.User;
import com.frro.bus.ticket.features.identity.repositories.UserRepository;
import com.frro.bus.ticket.features.journey.entities.Trip;
import com.frro.bus.ticket.features.journey.repositories.TripRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

@Service
@RequiredArgsConstructor
public class ProcessorServiceImpl implements ProcessorService {
    private final TicketRepository ticketRepository;
    private final TripRepository tripRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final TicketMapper ticketMapper;
    private final PriceCalculationService priceCalculationService;
    private final EntityManager entityManager;
    private final ObjectMapper objectMapper = JsonMapper.builder().build();

    @Override
    @Transactional
    public TicketFullDTO createTicket(CreateTicketDTO ticketRequest) {
        Trip trip = tripRepository.findById(ticketRequest.tripId())
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", ticketRequest.tripId()));

        Seat seat = seatRepository.findById(ticketRequest.seatId())
                .orElseThrow(() -> new ResourceNotFoundException("Seat", "id", ticketRequest.seatId()));

        User user = userRepository.findById(ticketRequest.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", ticketRequest.userId()));

        if (trip.getDepartureDate().isBefore(ZonedDateTime.now(ZoneOffset.UTC).plusHours(24))) {
            throw new BusinessException("Cannot book ticket: trip departs in less than 24 hours.");
        }

        ticketRepository.findByTripIdAndSeatIdAndIsCancelledFalse(ticketRequest.tripId(), ticketRequest.seatId())
                .ifPresent(ticket -> {
                    throw new DuplicateResourceException("Ticket", "trip+seat combination",
                            "trip=" + ticketRequest.tripId() + ", seat=" + ticketRequest.seatId());
                });

        long bookedSeats = ticketRepository.countByTripIdAndIsCancelledFalse(ticketRequest.tripId());
        if (bookedSeats >= trip.getBus().getTotalCapacity()) {
            throw new BusinessException("Trip is full. No available seats.");
        }

        Ticket ticket = ticketMapper.toTicket(ticketRequest);
        ticket.setTrip(trip);
        ticket.setSeat(seat);
        ticket.setUser(user);
        ticket.setBookingTime(ZonedDateTime.now(ZoneOffset.UTC));
        ticket.setFinalPrice(priceCalculationService.calculateFinalPriceValue(trip, seat));

        Ticket savedTicket = ticketRepository.save(ticket);

        savedTicket.setToken(generateToken(savedTicket.getId(), savedTicket.getBookingTime(), trip, seat));
        ticketRepository.save(savedTicket);

        return ticketMapper.toTicketFullDTO(savedTicket);
    }

    @Override
    @Transactional
    public TicketFullDTO updateTicket(UpdateTicketDTO ticketRequest) {
        Ticket existingTicket = ticketRepository.findById(ticketRequest.id())
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", ticketRequest.id()));

        ticketRequest.userId().ifPresent(userId -> {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
            existingTicket.setUser(user);
        });

        Trip trip = existingTicket.getTrip();
        if (ticketRequest.tripId().isPresent()) {
            trip = tripRepository.findById(ticketRequest.tripId().get())
                    .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", ticketRequest.tripId().get()));
            existingTicket.setTrip(trip);
        }

        Seat seat = existingTicket.getSeat();
        if (ticketRequest.seatId().isPresent()) {
            seat = seatRepository.findById(ticketRequest.seatId().get())
                    .orElseThrow(() -> new ResourceNotFoundException("Seat", "id", ticketRequest.seatId().get()));
            existingTicket.setSeat(seat);
        }

        if (trip.getDepartureDate().isBefore(ZonedDateTime.now(ZoneOffset.UTC).plusHours(24))) {
            throw new BusinessException("Cannot update ticket: trip departs in less than 24 hours.");
        }

        if (ticketRequest.tripId().isPresent() || ticketRequest.seatId().isPresent()) {
            int finalTripId = trip.getId();
            int finalSeatId = seat.getId();
            ticketRepository.findByTripIdAndSeatIdAndIsCancelledFalse(finalTripId, finalSeatId)
                    .ifPresent(ticket -> {
                        if (ticket.getId() != existingTicket.getId()) {
                            throw new DuplicateResourceException("Ticket", "trip+seat combination",
                                    "trip=" + finalTripId + ", seat=" + finalSeatId);
                        }
                    });

            existingTicket.setFinalPrice(priceCalculationService.calculateFinalPriceValue(trip, seat));
            existingTicket.setToken(generateToken(existingTicket.getId(), existingTicket.getBookingTime(), trip, seat));
        }

        Ticket savedTicket = ticketRepository.save(existingTicket);
        return ticketMapper.toTicketFullDTO(savedTicket);
    }

    @Override
    public TicketFullDTO deleteTicket(int id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", id));
        ticketRepository.deleteById(id);
        return ticketMapper.toTicketFullDTO(ticket);
    }

    @Override
    @Transactional
    public TicketFullDTO cancelTicket(int id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", id));

        if (ticket.isCancelled()) {
            throw new BusinessException("Ticket is already cancelled.");
        }

        ticket.setCancelled(true);
        Ticket savedTicket = ticketRepository.save(ticket);
        entityManager.flush();
        entityManager.refresh(savedTicket);
        return ticketMapper.toTicketFullDTO(savedTicket);
    }

    @Override
    public BigDecimal getFinalPrice(GetTicketFinalPriceDTO request) {
        Trip trip = tripRepository.findById(request.tripId())
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", request.tripId()));

        Seat seat = seatRepository.findById(request.seatId())
                .orElseThrow(() -> new ResourceNotFoundException("Seat", "id", request.seatId()));

        return priceCalculationService.calculateFinalPriceValue(trip, seat);
    }

    private String generateToken(int ticketId, ZonedDateTime bookingTime, Trip trip, Seat seat) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("ticketId", ticketId);
        data.put("bookingTime", bookingTime.toString());
        data.put("seat", Map.of(
                "letter", String.valueOf(seat.getLetter()),
                "number", seat.getNumber(),
                "seatType", seat.getSeatType().getName()));
        data.put("trip", Map.of(
                "departureDate", trip.getDepartureDate().toString(),
                "arrivalDate", trip.getArrivalDate().toString()));
        data.put("bus", Map.of(
                "plateNumber", trip.getBus().getPlateNumber()));

        try {
            return objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            throw new BusinessException("Failed to generate token");
        }
    }
}
