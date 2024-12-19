package com.practice.api.service.impl;

import com.practice.api.dao.TicketRepository;
import com.practice.api.exception.TicketNotFoundException;
import com.practice.api.model.Status;
import com.practice.api.model.Ticket;
import com.practice.api.model.TicketRequest;
import com.practice.api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Ticket createTicket(TicketRequest ticketRequest) {
        Ticket ticket = Ticket.builder()
                .description(ticketRequest.getDescription())
                .status(String.valueOf(Status.NEW))
                .build();
        return ticketRepository.save(ticket);    }

    @Override
    public Ticket updateTicket(Long ticketId, TicketRequest ticketRequest) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket id:"+ticketId+" not found !!"));
        ticket.setDescription(ticketRequest.getDescription());
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicket(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket id:"+ticketId+" not found !!"));    }
}
