package com.frro.bus.ticket.features.booking.controllers;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.common.dto.ApiResponse;
import com.frro.bus.ticket.common.security.endpointhelpers.AdminEndpoint;
import com.frro.bus.ticket.common.security.endpointhelpers.AuthenticatedEndpoint;
import com.frro.bus.ticket.features.booking.dtos.CreateTicketDTO;
import com.frro.bus.ticket.features.booking.dtos.GetTicketFinalPriceDTO;
import com.frro.bus.ticket.features.booking.dtos.TicketFullDTO;
import com.frro.bus.ticket.features.booking.dtos.UpdateTicketDTO;
import com.frro.bus.ticket.features.booking.services.processor.ProcessorService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/booking/processor")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class ProcessorController {

    private final ProcessorService processorService;

    @AuthenticatedEndpoint
    @PostMapping("/tickets")
    public ResponseEntity<ApiResponse<TicketFullDTO>> createTicket(@Valid @RequestBody CreateTicketDTO ticketRequest) {
        TicketFullDTO savedTicket = processorService.createTicket(ticketRequest);
        return ResponseEntity.ok(ApiResponse.success("Ticket created successfully", savedTicket));
    }

    @AdminEndpoint
    @PatchMapping("/tickets")
    public ResponseEntity<ApiResponse<TicketFullDTO>> updateTicket(@Valid @RequestBody UpdateTicketDTO ticketRequest) {
        TicketFullDTO ticket = processorService.updateTicket(ticketRequest);
        return ResponseEntity.ok(ApiResponse.success("Ticket updated successfully", ticket));
    }

    @AuthenticatedEndpoint
    @PostMapping("/tickets/final-price")
    public ResponseEntity<ApiResponse<BigDecimal>> getFinalPrice(
            @Valid @RequestBody GetTicketFinalPriceDTO ticketRequest) {
        BigDecimal finalPrice = processorService.getTicketFinalPrice(ticketRequest);
        return ResponseEntity.ok(ApiResponse.success("Final price calculated successfully", finalPrice));
    }

    @AdminEndpoint
    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<ApiResponse<TicketFullDTO>> deleteTicket(@PathVariable int id) {
        TicketFullDTO ticket = processorService.deleteTicket(id);
        return ResponseEntity.ok(ApiResponse.success("Ticket deleted successfully", ticket));
    }

    @AuthenticatedEndpoint
    @PatchMapping("/tickets/{id}/cancel")
    public ResponseEntity<ApiResponse<TicketFullDTO>> cancelTicket(@PathVariable int id) {
        TicketFullDTO ticket = processorService.cancelTicket(id);
        return ResponseEntity.ok(ApiResponse.success("Ticket cancelled successfully", ticket));
    }

}
