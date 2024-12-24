package com.practice.api.advice;

import com.practice.api.exception.TicketNotFoundException;
import com.practice.api.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.logging.Logger;

/**
 * Global exception handler for handling exceptions in the ticket system.
 * Provides methods to handle specific and general exceptions and returns appropriate HTTP status codes and error messages.
 */
@RestControllerAdvice
public class TicketExceptionHandler {
    private static final Logger logger= Logger.getLogger(TicketExceptionHandler.class.getName());

    /**
     * Handles TicketNotFoundException and returns a BAD_REQUEST response.
     *
     * @param e the TicketNotFoundException instance
     * @return an ErrorResponse containing the HTTP status code and error message
     */
    @ExceptionHandler(TicketNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse ticketNotFoundException(TicketNotFoundException e){
        logger.warning("Inside ticketNotFoundException: "+e.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    /**
     * Handles MethodArgumentTypeMismatchException and returns a BAD_REQUEST response.
     *
     * @param e the MethodArgumentTypeMismatchException instance
     * @return an ErrorResponse containing the HTTP status code and error message
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        logger.warning("Inside methodArgumentTypeMismatchException: "+e.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Ticket id can only have numeric value!!");
    }

    /**
     * Handles general exceptions and returns an INTERNAL_SERVER_ERROR response.
     *
     * @param ex the Exception instance
     * @return an ErrorResponse containing the HTTP status code and a generic error message
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneralException(Exception ex) {
        logger.warning("Inside handleGeneralException: "+ex.getMessage());
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An error occurred: " + ex.getMessage());
    }
}
