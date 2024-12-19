package com.practice.api.controller;

import com.practice.api.model.Ticket;
import com.practice.api.model.TicketRequest;
import com.practice.api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping()
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketRequest ticketRequest){
        Ticket ticket = ticketService.createTicket(ticketRequest);
        return ResponseEntity.ok(ticket);
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long ticketId, @RequestBody TicketRequest ticketRequest) {
        Ticket ticket = ticketService.updateTicket(ticketId, ticketRequest);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long ticketId) {
        Ticket ticket = ticketService.getTicket(ticketId);
        return ResponseEntity.ok(ticket);
    }

}
