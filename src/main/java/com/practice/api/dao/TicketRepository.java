package com.practice.api.dao;

import com.practice.api.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Ticket entities.
 * Extends JpaRepository to provide CRUD operations for Ticket entities.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
