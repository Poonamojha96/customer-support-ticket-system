package com.practice.api.service.impl;

import com.practice.api.dao.TicketRepository;
import com.practice.api.exception.TicketNotFoundException;
import com.practice.api.model.Status;
import com.practice.api.model.Ticket;
import com.practice.api.model.TicketRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    private static final Logger logger = Logger.getLogger(TicketServiceImplTest.class.getName());

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTicket() {
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setDescription("Test description");

        Ticket ticket = Ticket.builder()
                .description("Test description")
                .status(String.valueOf(Status.NEW))
                .createdAt(new Date(System.currentTimeMillis()).toString())
                .updatedAt(new Date(System.currentTimeMillis()).toString())
                .build();

        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket createdTicket = ticketService.createTicket(ticketRequest);
        assertNotNull(createdTicket);
        assertEquals("Test description", createdTicket.getDescription());

        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    void testUpdateTicket() {
        Long ticketId = 1L;
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setDescription("Updated description");

        Ticket ticket = Ticket.builder()
                .ticketId(ticketId)
                .description("Test description")
                .status(String.valueOf(Status.NEW))
                .createdAt(new Date(System.currentTimeMillis()).toString())
                .updatedAt(new Date(System.currentTimeMillis()).toString())
                .build();

        when(ticketRepository.findById(anyLong())).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket updatedTicket = ticketService.updateTicket(ticketId, ticketRequest);
        assertNotNull(updatedTicket);
        assertEquals("Updated description", updatedTicket.getDescription());

        verify(ticketRepository, times(1)).findById(anyLong());
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    void testUpdateTicketNotFound() {
        Long ticketId = 1L;
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setDescription("Updated description");

        when(ticketRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(TicketNotFoundException.class, () -> {
            ticketService.updateTicket(ticketId, ticketRequest);
        });

        verify(ticketRepository, times(1)).findById(anyLong());
        verify(ticketRepository, times(0)).save(any(Ticket.class));
    }

    @Test
    void testGetTicket() {
        Long ticketId = 1L;

        Ticket ticket = Ticket.builder()
                .ticketId(ticketId)
                .description("Test description")
                .status(String.valueOf(Status.NEW))
                .createdAt(new Date(System.currentTimeMillis()).toString())
                .updatedAt(new Date(System.currentTimeMillis()).toString())
                .build();

        when(ticketRepository.findById(anyLong())).thenReturn(Optional.of(ticket));

        Ticket foundTicket = ticketService.getTicket(ticketId);
        assertNotNull(foundTicket);
        assertEquals("Test description", foundTicket.getDescription());

        verify(ticketRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGetTicketNotFound() {
        Long ticketId = 1L;

        when(ticketRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(TicketNotFoundException.class, () -> {
            ticketService.getTicket(ticketId);
        });

        verify(ticketRepository, times(1)).findById(anyLong());
    }

    @Test
    void testFallbackCreateTicket() {
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setDescription("Test description");

        Ticket fallbackTicket = ticketService.fallbackCreateTicket(ticketRequest, new Throwable("Database error"));
        assertNotNull(fallbackTicket);
        assertNull(fallbackTicket.getDescription());
    }
}
