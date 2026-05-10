package com.frro.bus.ticket.features.booking.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frro.bus.ticket.features.booking.dtos.CreateTicketDTO;
import com.frro.bus.ticket.features.booking.dtos.TicketDTO;
import com.frro.bus.ticket.features.booking.dtos.TicketFullDTO;
import com.frro.bus.ticket.features.booking.dtos.UpdateTicketDTO;
import com.frro.bus.ticket.features.booking.services.processor.ProcessorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/booking/processor")
@RequiredArgsConstructor
public class ProcessorController {
    private final ProcessorService processorService;

    @PostMapping("/tickets")
    public ResponseEntity<TicketFullDTO> createTicket(@RequestBody CreateTicketDTO ticketRequest) {
        TicketFullDTO savedTicket = processorService.createTicket(ticketRequest);
        return ResponseEntity.ok(savedTicket);
    }

    @PatchMapping("/tickets")
    public ResponseEntity<TicketFullDTO> updateTicket(@RequestBody UpdateTicketDTO ticketRequest) {
        Optional<TicketFullDTO> updatedTicket = processorService.updateTicket(ticketRequest);
        return updatedTicket.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<TicketFullDTO> deleteTicket(@PathVariable int id) {
        Optional<TicketFullDTO> deletedTicket = processorService.deleteTicket(id);
        return deletedTicket.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
