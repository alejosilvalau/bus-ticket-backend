package com.frro.bus.ticket.features.booking.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.common.dto.ApiResponse;
import com.frro.bus.ticket.features.booking.dtos.CreateTicketDTO;
import com.frro.bus.ticket.features.booking.dtos.TicketFullDTO;
import com.frro.bus.ticket.features.booking.dtos.UpdateTicketDTO;
import com.frro.bus.ticket.features.booking.services.processor.ProcessorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/booking/processor")
@RequiredArgsConstructor
public class ProcessorController {

    private static final Logger log = LoggerFactory.getLogger(ProcessorController.class);

    private final ProcessorService processorService;

    @PostMapping("/tickets")
    public ResponseEntity<ApiResponse<TicketFullDTO>> createTicket(@Valid @RequestBody CreateTicketDTO ticketRequest) {
        try {
            TicketFullDTO savedTicket = processorService.createTicket(ticketRequest);
            return ResponseEntity.ok(ApiResponse.success("Ticket created successfully", savedTicket));
        } catch (Exception e) {
            log.error("Failed to create ticket", e);
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to create ticket. Please try again later."));
        }
    }

    @PatchMapping("/tickets")
    public ResponseEntity<ApiResponse<TicketFullDTO>> updateTicket(@Valid @RequestBody UpdateTicketDTO ticketRequest) {
        try {
            return processorService.updateTicket(ticketRequest)
                    .map(ticket -> ResponseEntity.ok(ApiResponse.success("Ticket updated successfully", ticket)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Ticket not found with id: " + ticketRequest.id())));
        } catch (Exception e) {
            log.error("Failed to update ticket with id: {}", ticketRequest.id(), e);
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to update ticket. Please try again later."));
        }
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<ApiResponse<TicketFullDTO>> deleteTicket(@PathVariable int id) {
        try {
            return processorService.deleteTicket(id)
                    .map(ticket -> ResponseEntity.ok(ApiResponse.success("Ticket deleted successfully", ticket)))
                    .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("Ticket not found with id: " + id)));
        } catch (Exception e) {
            log.error("Failed to delete ticket with id: {}", id, e);
            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to delete ticket. Please try again later."));
        }
    }
}
