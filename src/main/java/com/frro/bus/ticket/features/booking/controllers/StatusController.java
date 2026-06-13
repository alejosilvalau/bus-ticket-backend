package com.frro.bus.ticket.features.booking.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.common.dto.ApiResponse;
import com.frro.bus.ticket.features.booking.dtos.TicketFullDTO;
import com.frro.bus.ticket.features.booking.dtos.SearchTicketDTO;
import com.frro.bus.ticket.features.booking.services.status.StatusService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/booking/status")
@RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;

    @GetMapping("/tickets")
    public ResponseEntity<ApiResponse<Page<TicketFullDTO>>> findAllTickets(Pageable pageable) {
        try {
            Page<TicketFullDTO> tickets = statusService.findAllTickets(pageable);
            return ResponseEntity.ok(ApiResponse.success("Tickets retrieved successfully", tickets));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to retrieve tickets: " + e.getMessage()));
        }
    }

    @GetMapping("/tickets/search")
    public ResponseEntity<ApiResponse<Page<TicketFullDTO>>> searchTickets(@RequestBody SearchTicketDTO searchCriteria, Pageable pageable) {
        try {
            Page<TicketFullDTO> tickets = statusService.searchTickets(searchCriteria, pageable);
            return ResponseEntity.ok(ApiResponse.success("Tickets searched successfully", tickets));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to search tickets: " + e.getMessage()));
        }
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<ApiResponse<TicketFullDTO>> findTicketById(@PathVariable int id) {
        try {
            return statusService.findTicketById(id)
                    .map(ticket -> ResponseEntity.ok(ApiResponse.success("Ticket retrieved successfully", ticket)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Ticket not found with id: " + id)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to retrieve ticket: " + e.getMessage()));
        }
    }
}
