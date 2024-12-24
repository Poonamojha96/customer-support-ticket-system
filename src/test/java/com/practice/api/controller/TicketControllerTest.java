package com.practice.api.controller;

import com.practice.api.model.AuthRequest;
import com.practice.api.model.Status;
import com.practice.api.model.Ticket;
import com.practice.api.model.TicketRequest;
import com.practice.api.service.TicketService;
import com.practice.api.service.impl.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TicketService ticketService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private TicketController ticketController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
    }

    @Test
    void testCreateTicket() throws Exception {
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setDescription("Test description");

        Ticket ticket = Ticket.builder()
                .description("Test description")
                .status(String.valueOf(Status.NEW))
                .createdAt(new Date(System.currentTimeMillis()).toString())
                .updatedAt(new Date(System.currentTimeMillis()).toString())
                .build();

        when(ticketService.createTicket(any(TicketRequest.class))).thenReturn(ticket);

        mockMvc.perform(post("/v1/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"Test description\"}"))
                .andExpect(status().isOk());
        verify(ticketService, times(1)).createTicket(any(TicketRequest.class));
        verifyNoMoreInteractions(ticketService);
    }

    @Test
    void testUpdateTicket() throws Exception {
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

        when(ticketService.updateTicket(anyLong(), any(TicketRequest.class))).thenReturn(ticket);

        mockMvc.perform(put("/v1/tickets/{ticketId}", ticketId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"Updated description\"}"))
                .andExpect(status().isOk());
        verify(ticketService, times(1)).updateTicket(anyLong(), any(TicketRequest.class));
        verifyNoMoreInteractions(ticketService);
    }

    @Test
    void testGetTicket() throws Exception {
        Long ticketId = 1L;

        Ticket ticket = Ticket.builder()
                .ticketId(ticketId)
                .description("Test description")
                .status(String.valueOf(Status.NEW))
                .createdAt(new Date(System.currentTimeMillis()).toString())
                .updatedAt(new Date(System.currentTimeMillis()).toString())
                .build();

        when(ticketService.getTicket(anyLong())).thenReturn(ticket);

        mockMvc.perform(get("/v1/tickets/{ticketId}", ticketId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(ticketService, times(1)).getTicket(anyLong());
        verifyNoMoreInteractions(ticketService);
    }

    @Test
    void testGenerateAuthToken() throws Exception {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("testuser");

        when(jwtService.generateToken(anyString())).thenReturn("mockToken");

        mockMvc.perform(post("/v1/tickets/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testuser\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("mockToken"));

        verify(jwtService, times(1)).generateToken(anyString());
        verifyNoMoreInteractions(jwtService);
    }
}
