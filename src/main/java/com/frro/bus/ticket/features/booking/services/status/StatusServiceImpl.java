package com.frro.bus.ticket.features.booking.services.status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.frro.bus.ticket.common.dto.PageResponse;
import com.frro.bus.ticket.common.exceptions.BusinessException;
import com.frro.bus.ticket.common.exceptions.ResourceNotFoundException;
import com.frro.bus.ticket.common.utils.PaginationUtils;
import com.frro.bus.ticket.features.booking.dtos.SearchTicketDTO;
import com.frro.bus.ticket.features.booking.dtos.TicketFullDTO;
import com.frro.bus.ticket.features.booking.dtos.TokenDTO;
import com.frro.bus.ticket.features.booking.entities.Ticket;
import com.frro.bus.ticket.features.booking.mappers.TicketMapper;
import com.frro.bus.ticket.features.booking.repositories.TicketRepository;
import com.frro.bus.ticket.features.fleet.entities.Seat;
import com.frro.bus.ticket.features.journey.entities.Trip;

import lombok.RequiredArgsConstructor;

import jakarta.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final HttpServletRequest request;

    @Override
    public PageResponse<TicketFullDTO> findAllTickets(Pageable pageable) {
        Page<TicketFullDTO> page = ticketRepository.findAll(pageable).map(ticketMapper::toTicketFullDTO);
        return PaginationUtils.toPageResponse(page);
    }

    @Override
    public PageResponse<TicketFullDTO> searchTickets(SearchTicketDTO searchCriteria, Pageable pageable) {
        Integer authenticatedUserId = getAuthenticatedUserId();
        Integer userIdFilter = searchCriteria.userId().orElse(authenticatedUserId);
        if (!isAdmin() && !userIdFilter.equals(authenticatedUserId)) {
            throw new BusinessException("You can only search your own tickets");
        }

        Page<TicketFullDTO> page = ticketRepository.searchTickets(
                searchCriteria.startFinalPrice().orElse(null),
                searchCriteria.endFinalPrice().orElse(null),
                searchCriteria.startBookingTime().orElse(null),
                searchCriteria.endBookingTime().orElse(null),
                searchCriteria.isCancelled().orElse(null),
            isAdmin() ? searchCriteria.userId().orElse(null) : authenticatedUserId,
                searchCriteria.tripId().orElse(null),
                searchCriteria.seatId().orElse(null),
                pageable)
                .map(ticketMapper::toTicketFullDTO);
        return PaginationUtils.toPageResponse(page);
    }

    @Override
    public TicketFullDTO findTicketById(int id) {
        Ticket ticket = ticketRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", id));

        ensureCanAccessTicket(ticket);
        return ticketMapper.toTicketFullDTO(ticket);
    }

    @Override
    public TokenDTO getToken(int ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", ticketId));

        ensureCanAccessTicket(ticket);

        Trip trip = ticket.getTrip();
        Seat seat = ticket.getSeat();

        return new TokenDTO(
                trip.getBus().getPlateNumber(),
                trip.getDriver().getFirstName(),
                trip.getLocationOrigin().getCityName(),
                trip.getLocationDestination().getCityName(),
                trip.getDepartureDate(),
                trip.getArrivalDate(),
                seat.getLetter(),
                seat.getNumber(),
                seat.getSeatType().getName(),
                ticket.getBookingTime());
    }

    private int getAuthenticatedUserId() {
        Object userId = request.getAttribute("userId");
        if (userId == null) {
            throw new BusinessException("No authenticated user found");
        }
        return (int) userId;
    }

    private boolean isAdmin() {
        Object isAdmin = request.getAttribute("isAdmin");
        return isAdmin instanceof Boolean && (Boolean) isAdmin;
    }

    private void ensureCanAccessTicket(Ticket ticket) {
        if (isAdmin()) {
            return;
        }

        int authenticatedUserId = getAuthenticatedUserId();
        if (ticket.getUser() == null || ticket.getUser().getId() != authenticatedUserId) {
            throw new BusinessException("You can only access your own tickets");
        }
    }
}
