package com.frro.bus.ticket.features.booking.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.common.dto.ApiResponse;
import com.frro.bus.ticket.common.dto.PaginationMeta;
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
    public ResponseEntity<ApiResponse<List<TicketFullDTO>>> findAllTickets(Pageable pageable) {
        Page<TicketFullDTO> tickets = statusService.findAllTickets(pageable);
        return ResponseEntity.ok(ApiResponse.success(tickets.getContent(), PaginationMeta.fromPage(tickets)));
    }

    @GetMapping("/tickets/search")
    public ResponseEntity<ApiResponse<List<TicketFullDTO>>> searchTickets(@RequestBody SearchTicketDTO searchCriteria, Pageable pageable) {
        Page<TicketFullDTO> tickets = statusService.searchTickets(searchCriteria, pageable);
        return ResponseEntity.ok(ApiResponse.success(tickets.getContent(), PaginationMeta.fromPage(tickets)));
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<ApiResponse<TicketFullDTO>> findTicketById(@PathVariable int id) {
        return statusService.findTicketById(id)
                .map(ticket -> ResponseEntity.ok(ApiResponse.success(ticket)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Ticket not found")));
    }
}
