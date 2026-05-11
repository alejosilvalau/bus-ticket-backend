package com.frro.bus.ticket.features.booking.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<TicketFullDTO>> findAllTickets() {
        List<TicketFullDTO> tickets = statusService.findAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<TicketFullDTO> findTicketById(@PathVariable int id) {
        Optional<TicketFullDTO> ticket = statusService.findTicketById(id);
        return ticket.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/tickets/search")
    public ResponseEntity<List<TicketFullDTO>> searchTickets(@RequestBody SearchTicketDTO searchCriteria) {
        List<TicketFullDTO> tickets = statusService.searchTickets(searchCriteria);
        return ResponseEntity.ok(tickets);
    }
}
