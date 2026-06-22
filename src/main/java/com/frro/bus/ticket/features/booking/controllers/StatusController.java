package com.frro.bus.ticket.features.booking.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.common.dto.ApiResponse;
import com.frro.bus.ticket.common.dto.PageResponse;
import com.frro.bus.ticket.common.security.endpointhelpers.AdminEndpoint;
import com.frro.bus.ticket.common.security.endpointhelpers.AuthenticatedEndpoint;
import com.frro.bus.ticket.features.booking.dtos.TicketFullDTO;
import com.frro.bus.ticket.features.booking.dtos.SearchTicketDTO;
import com.frro.bus.ticket.features.booking.services.status.StatusService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/booking/status")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class StatusController {

    private final StatusService statusService;

    @AdminEndpoint
    @GetMapping("/tickets")
    public ResponseEntity<ApiResponse<PageResponse<TicketFullDTO>>> findAllTickets(Pageable pageable) {
        PageResponse<TicketFullDTO> tickets = statusService.findAllTickets(pageable);
        return ResponseEntity.ok(ApiResponse.success("Tickets retrieved successfully", tickets));
    }

    @AuthenticatedEndpoint
    @PostMapping("/tickets/search")
    public ResponseEntity<ApiResponse<PageResponse<TicketFullDTO>>> searchTickets(
            @Valid @RequestBody SearchTicketDTO searchCriteria, Pageable pageable) {
        PageResponse<TicketFullDTO> tickets = statusService.searchTickets(searchCriteria, pageable);
        return ResponseEntity.ok(ApiResponse.success("Tickets searched successfully", tickets));
    }

    @AuthenticatedEndpoint
    @GetMapping("/tickets/{id}")
    public ResponseEntity<ApiResponse<TicketFullDTO>> findTicketById(@PathVariable int id) {
        TicketFullDTO ticket = statusService.findTicketById(id);
        return ResponseEntity.ok(ApiResponse.success("Ticket retrieved successfully", ticket));
    }
}
