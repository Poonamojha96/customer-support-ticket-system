package com.practice.api.service.impl;

import com.practice.api.dao.TicketRepository;
import com.practice.api.exception.TicketNotFoundException;
import com.practice.api.model.Status;
import com.practice.api.model.Ticket;
import com.practice.api.model.TicketRequest;
import com.practice.api.service.TicketService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Logger;

/**
 * Service implementation class for managing tickets.
 * Provides methods to create, update, and retrieve tickets.
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;
    private static final Logger logger= Logger.getLogger(TicketServiceImpl.class.getName());

    /**
     * Creates a new ticket with the provided ticket request data.
     *
     * @param ticketRequest the request object containing ticket details
     * @return the created ticket
     */
    @Override
    @CircuitBreaker(name = "h2dbCircuitBreaker", fallbackMethod = "fallbackCreateTicket")
    public Ticket createTicket(TicketRequest ticketRequest) {
        logger.info("TicketServiceImpl: Inside createTicket method to save the customer support request");
        Ticket ticket = Ticket.builder()
                .description(ticketRequest.getDescription())
                .status(String.valueOf(Status.NEW))
                .createdAt((new Date(System.currentTimeMillis())).toString())
                .updatedAt((new Date(System.currentTimeMillis())).toString())
                .build();
        return ticketRepository.save(ticket);
    }

    /**
     * Updates an existing ticket with the provided ticket ID and request data.
     *
     * @param ticketId      the ID of the ticket to be updated
     * @param ticketRequest the request object containing updated ticket details
     * @return the updated ticket
     * @throws TicketNotFoundException if the ticket ID is not found
     */
    @Override
    public Ticket updateTicket(Long ticketId, TicketRequest ticketRequest) {
        logger.info("TicketServiceImpl: Inside updateTicket method to update the customer support request");
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket id:" + ticketId + " not found !!"));
        ticket.setDescription(ticketRequest.getDescription());
        ticket.setUpdatedAt((new Date(System.currentTimeMillis())).toString());
        return ticketRepository.save(ticket);
    }

    /**
     * Retrieves a ticket by its ID.
     *
     * @param ticketId the ID of the ticket to be retrieved
     * @return the retrieved ticket
     * @throws TicketNotFoundException if the ticket ID is not found
     */
    @Override
    public Ticket getTicket(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket id:" + ticketId + " not found !!"));
    }


    //fallback method when the h2 database is down/ or a table is not found
    public Ticket fallbackCreateTicket() {
        logger.warning("Database is unreachable. Falling back to default create ticket.");
        return new Ticket();
    }
}
