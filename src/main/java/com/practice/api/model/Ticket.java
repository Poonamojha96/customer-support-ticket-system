package com.practice.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class representing a support ticket.
 * Contains details such as ticket ID, description, status, and timestamps for creation and last update.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ticket {

    /**
     * The unique identifier for the ticket.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    /**
     * The description of the ticket.
     */
    private String description;

    /**
     * The status of the ticket.
     */
    private String status;

    /**
     * The timestamp when the ticket was created.
     */
    private String createdAt;

    /**
     * The timestamp when the ticket was last updated.
     */
    private String updatedAt;
}
