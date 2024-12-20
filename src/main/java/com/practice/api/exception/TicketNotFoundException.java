package com.practice.api.exception;

/**
 * Exception thrown when a ticket is not found in the system.
 * This is a runtime exception that indicates that the requested ticket could not be located.
 */
public class TicketNotFoundException extends RuntimeException {

    /**
     * Constructs a new TicketNotFoundException with the specified error message.
     *
     * @param errorMessage the detail message explaining the reason for the exception
     */
    public TicketNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
