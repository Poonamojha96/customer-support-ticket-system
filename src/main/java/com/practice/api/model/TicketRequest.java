package com.practice.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for ticket creation requests.
 * Contains the details needed to create a new support ticket.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {

    /**
     * The description of the ticket.
     */
    private String description;
}
