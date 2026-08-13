package com.frro.bus.ticket.features.booking.services.processor;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import org.springframework.stereotype.Service;

import com.frro.bus.ticket.common.exceptions.BusinessException;
import com.frro.bus.ticket.common.exceptions.DuplicateResourceException;
import com.frro.bus.ticket.common.exceptions.ResourceNotFoundException;
import com.frro.bus.ticket.features.booking.dtos.CreateTicketDTO;
import com.frro.bus.ticket.features.booking.dtos.TicketFullDTO;
import com.frro.bus.ticket.features.booking.dtos.UpdateTicketDTO;
import com.frro.bus.ticket.features.booking.dtos.GetTicketFinalPriceDTO;
import com.frro.bus.ticket.features.booking.entities.Ticket;
import com.frro.bus.ticket.features.booking.mappers.TicketMapper;
import com.frro.bus.ticket.features.booking.repositories.TicketRepository;
import com.frro.bus.ticket.features.fleet.entities.Bus;
import com.frro.bus.ticket.features.fleet.entities.Seat;
import com.frro.bus.ticket.features.identity.entities.Driver;
import com.frro.bus.ticket.features.fleet.repositories.SeatRepository;
import com.frro.bus.ticket.features.identity.entities.User;
import com.frro.bus.ticket.features.identity.repositories.UserRepository;
import com.frro.bus.ticket.features.journey.entities.Trip;
import com.frro.bus.ticket.features.journey.repositories.TripRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProcessorServiceImpl implements ProcessorService {
    private final TicketRepository ticketRepository;
    private final TripRepository tripRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final TicketMapper ticketMapper;

    @Override
    public TicketFullDTO createTicket(CreateTicketDTO ticketRequest) {
        Ticket ticket = ticketMapper.toTicket(ticketRequest);
        ticket.setTrip(validateTripRelationship(ticketRequest.tripId()));
        ticket.setSeat(validateSeatRelationship(ticketRequest.seatId()));
        ticket.setUser(validateUserRelationship(ticketRequest.userId()));

        validateTripDepartureTime(ticket.getTrip());

        validateBusRelationship(ticket.getTrip().getBus());

        validateDriverRelationship(ticket.getTrip().getDriver());

        validateSeatBelongsToTrip(ticket.getSeat(), ticket.getTrip());

        int excludeTicketId = 0; // No existing ticket to exclude during creation
        validateTripAndSeatUniqueness(ticket.getTrip().getId(), ticket.getSeat().getId(), excludeTicketId);

        validateSeatAvailability(ticket.getTrip());

        ticket.setBookingTime(ZonedDateTime.now(ZoneOffset.UTC));
        ticket.setFinalPrice(calculateFinalPriceValue(ticket.getTrip(), ticket.getSeat()));

        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.toTicketFullDTO(savedTicket);
    }

    @Override
    public TicketFullDTO updateTicket(UpdateTicketDTO ticketRequest) {
        Ticket existingTicket = ticketRepository.findById(ticketRequest.id())
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", ticketRequest.id()));

        if (existingTicket.isCancelled()) {
            throw new BusinessException("Cannot update a cancelled ticket.");
        }

        ticketRequest.tripId().ifPresent(tripId -> {
            existingTicket.setTrip(validateTripRelationship(tripId));
        });

        ticketRequest.seatId().ifPresent(seatId -> {
            existingTicket.setSeat(validateSeatRelationship(seatId));
        });

        ticketRequest.userId().ifPresent(userId -> {
            existingTicket.setUser(validateUserRelationship(userId));
        });

        validateTripDepartureTime(existingTicket.getTrip());

        validateBusRelationship(existingTicket.getTrip().getBus());

        validateDriverRelationship(existingTicket.getTrip().getDriver());

        if (ticketRequest.tripId().isPresent()) {
            validateSeatAvailability(existingTicket.getTrip());
        }

        if (ticketRequest.tripId().isPresent() || ticketRequest.seatId().isPresent()) {
            validateSeatBelongsToTrip(existingTicket.getSeat(), existingTicket.getTrip());

            validateTripAndSeatUniqueness(existingTicket.getTrip().getId(), existingTicket.getSeat().getId(),
                    existingTicket.getId());

            existingTicket.setFinalPrice(calculateFinalPriceValue(existingTicket.getTrip(),
                    existingTicket.getSeat()));
        }

        Ticket savedTicket = ticketRepository.save(existingTicket);
        return ticketMapper.toTicketFullDTO(savedTicket);
    }

    private void validateTripDepartureTime(Trip trip) {
        long departureTimeBufferHours = 24;
        if (trip.getDepartureDate().isBefore(ZonedDateTime.now(ZoneOffset.UTC).plusHours(departureTimeBufferHours))) {
            throw new BusinessException("Cannot book or update ticket: trip departs in less than 24 hours.");
        }
    }

    private void validateTripAndSeatUniqueness(int tripId, int seatId, int excludeTicketId) {
        ticketRepository.findByTripIdAndSeatIdAndIsCancelledFalse(tripId, seatId)
                .ifPresent(ticket -> {
                    if (ticket.getId() != excludeTicketId) {
                        throw new DuplicateResourceException("Ticket", "trip+seat+isCancelledFalse",
                                "trip=" + tripId + ", seat=" + seatId);
                    }
                });
    }

    private void validateSeatAvailability(Trip trip) {
        long bookedSeats = ticketRepository.countByTripIdAndIsCancelledFalse(trip.getId());
        if (bookedSeats >= trip.getBus().getTotalCapacity()) {
            throw new BusinessException("Trip is full. No available seats.");
        }
    }

    private void validateSeatBelongsToTrip(Seat seat, Trip trip) {
        if (seat.getBus().getId() != trip.getBus().getId()) {
            throw new BusinessException("Seat does not belong to the trip's bus.");
        }
    }

    @Override
    public TicketFullDTO deleteTicket(int id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", id));
        ticketRepository.deleteById(id);
        return ticketMapper.toTicketFullDTO(ticket);
    }

    @Override
    public TicketFullDTO cancelTicket(int id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", id));

        if (ticket.isCancelled()) {
            throw new BusinessException("Ticket is already cancelled.");
        }

        ticket.setCancelled(true);
        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.toTicketFullDTO(savedTicket);
    }

    @Override
    public BigDecimal getTicketFinalPrice(GetTicketFinalPriceDTO ticketRequest) {
        Trip trip = validateTripRelationship(ticketRequest.tripId());
        Seat seat = validateSeatRelationship(ticketRequest.seatId());

        validateTripDepartureTime(trip);

        validateBusRelationship(trip.getBus());

        validateDriverRelationship(trip.getDriver());

        validateSeatBelongsToTrip(seat, trip);

        int excludeTicketId = 0; // No existing ticket to exclude during price check
        validateTripAndSeatUniqueness(trip.getId(), seat.getId(), excludeTicketId);

        validateSeatAvailability(trip);

        return calculateFinalPriceValue(trip, seat);
    }

    private void validateBusRelationship(Bus bus) {
        if (!bus.isActive()) {
            throw new BusinessException("Bus is not active.");
        }
    }

    private void validateDriverRelationship(Driver driver) {
        if (!driver.isActive()) {
            throw new BusinessException("Driver is not active.");
        }
    }

    private Seat validateSeatRelationship(int seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new ResourceNotFoundException("Seat", "id", seatId));
        if (!seat.isActive()) {
            throw new BusinessException("Seat is not active.");
        }
        return seat;
    }

    private Trip validateTripRelationship(int tripId) {
        return tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip", "id", tripId));
    }

    private User validateUserRelationship(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    private BigDecimal calculateFinalPriceValue(Trip trip, Seat seat) {
        BigDecimal basePrice = trip.getBasePrice();
        BigDecimal seatUpcharge = seat.getSeatType().getUpcharge();
        return basePrice.add(seatUpcharge);
    }
}
