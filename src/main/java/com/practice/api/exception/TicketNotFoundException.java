package com.practice.api.exception;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
