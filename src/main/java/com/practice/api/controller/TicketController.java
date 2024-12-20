package com.practice.api.controller;

import com.practice.api.exception.TicketNotFoundException;
import com.practice.api.model.AuthRequest;
import com.practice.api.model.Ticket;
import com.practice.api.model.TicketRequest;
import com.practice.api.service.TicketService;
import com.practice.api.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

/**
 * REST controller for creating and managing customer support tickets.
 * Provides endpoints for creating, updating, retrieving tickets, and generating authentication tokens.
 */
@RestController
@RequestMapping("/v1/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private JwtService jwtService;

    private static final Logger logger= Logger.getLogger(TicketController.class.getName());


    /**
     * Creates a new support ticket.
     *
     * @param ticketRequest the request object containing ticket details
     * @return the created ticket
     */
    @PostMapping()
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketRequest ticketRequest) {
        logger.info("TicketController : Inside createTicket method: ticket request: "+ticketRequest);
        Ticket ticket = ticketService.createTicket(ticketRequest);
        return ResponseEntity.ok(ticket);
    }

    /**
     * Updates an existing support ticket.
     *
     * @param ticketId the ID of the ticket to be updated
     * @param ticketRequest the request object containing updated ticket details
     * @return the updated ticket
     * @throws TicketNotFoundException if the ticket ID is not found
     */
    @PutMapping("/{ticketId}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long ticketId, @RequestBody TicketRequest ticketRequest) {
        logger.info("TicketController : Inside updateTicket method: ticket request: "+ticketRequest);
        Ticket ticket = null;
        if (ticketId != null) {
            ticket = ticketService.updateTicket(ticketId, ticketRequest);
        } else {
            logger.info("Ticket id not found exception: ticket id: "+ticketId);
            throw new TicketNotFoundException("No ticket ID found !!");
        }
        return ResponseEntity.ok(ticket);
    }

    /**
     * Retrieves a support ticket by its ID.
     *
     * @param ticketId the ID of the ticket to be retrieved
     * @return the retrieved ticket
     * @throws TicketNotFoundException if the ticket ID is not found
     */
    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long ticketId) {
        logger.info("TicketController : Inside getTicket method: ticket id: "+ticketId);
        Ticket ticket = null;
        if (ticketId != null) {
            ticket = ticketService.getTicket(ticketId);
        } else {
            logger.info("Ticket id not found exception: ticket id: "+ticketId);
            throw new TicketNotFoundException("No ticket ID found !!");
        }
        return ResponseEntity.ok(ticket);
    }

    /**
     * Generates an authentication token for a user.
     *
     * @param authRequest the request object containing authentication details
     * @return the generated authentication token
     */
    @PostMapping("/authenticate")
    public String generateAuthToken(@RequestBody AuthRequest authRequest) {
        logger.info("Inside generateAuthToken method with authRequest: "+authRequest);
        return jwtService.generateToken(authRequest.getUsername());
    }
}
