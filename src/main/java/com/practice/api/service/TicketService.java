package com.practice.api.service;

import com.practice.api.model.Ticket;
import com.practice.api.model.TicketRequest;

public interface TicketService {
    Ticket createTicket(TicketRequest ticketRequest);

    Ticket updateTicket(Long id, TicketRequest ticketRequest);

    Ticket getTicket(Long id);
}
