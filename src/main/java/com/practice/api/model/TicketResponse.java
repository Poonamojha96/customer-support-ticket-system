package com.practice.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {

    private Long ticketId;
    private String description;
    private String status;
    private String createdAt;
    private String updatedAt;
}
