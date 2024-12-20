package com.practice.api.service;

import com.practice.api.model.Ticket;
import com.practice.api.model.TicketRequest;

/**
 * Service interface for managing tickets.
 * Provides methods to create, update, and retrieve tickets.
 */
public interface TicketService {

    /**
     * Creates a new ticket with the provided ticket request data.
     *
     * @param ticketRequest the request object containing ticket details
     * @return the created ticket
     */
    Ticket createTicket(TicketRequest ticketRequest);

    /**
     * Updates an existing ticket with the provided ticket ID and request data.
     *
     * @param id            the ID of the ticket to be updated
     * @param ticketRequest the request object containing updated ticket details
     * @return the updated ticket
     */
    Ticket updateTicket(Long id, TicketRequest ticketRequest);

    /**
     * Retrieves a ticket by its ID.
     *
     * @param id the ID of the ticket to be retrieved
     * @return the retrieved ticket
     */
    Ticket getTicket(Long id);
}
