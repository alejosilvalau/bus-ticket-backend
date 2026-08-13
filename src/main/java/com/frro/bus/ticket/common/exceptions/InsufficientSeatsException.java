package com.frro.bus.ticket.common.exceptions;

public class InsufficientSeatsException extends BusinessException {

    public InsufficientSeatsException(String message) {
        super(message);
    }

    public InsufficientSeatsException() {
        super("Insufficient seats available");
    }
}
