package com.frro.bus.ticket.common.exceptions;

public class InvalidCredentialsException extends BusinessException {

    public InvalidCredentialsException(String message) {
        super(message);
    }

    public InvalidCredentialsException() {
        super("Invalid email or password");
    }
}
