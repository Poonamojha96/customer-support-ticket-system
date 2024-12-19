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

@RestController
@RequestMapping("/v1/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private JwtService jwtService;

    /**
     *
     * @param ticketRequest
     * @return
     */
    @PostMapping()
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketRequest ticketRequest){
        Ticket ticket = ticketService.createTicket(ticketRequest);
        return ResponseEntity.ok(ticket);
    }

    /**
     *
     * @param ticketId
     * @param ticketRequest
     * @return
     */
    @PutMapping("/{ticketId}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long ticketId, @RequestBody TicketRequest ticketRequest) {
        Ticket ticket= null;
        if(null != ticketId){
            ticket = ticketService.updateTicket(ticketId, ticketRequest);
        }else{
            throw new TicketNotFoundException("No ticket ID found !!");
        }
        return ResponseEntity.ok(ticket);
    }

    /**
     *
     * @param ticketId
     * @return
     */
    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long ticketId) {
        Ticket ticket= null;
        if(null != ticketId) {
            ticket = ticketService.getTicket(ticketId);
        }else
            throw new TicketNotFoundException("No ticket ID found !!");
        return ResponseEntity.ok(ticket);
    }

    /**
     *
     * @param authRequest
     * @return
     */
    @PostMapping("/authenticate")
    public String generateAuthToken(@RequestBody AuthRequest authRequest){
        return jwtService.generateToken(authRequest.getUsername());
    }

}
