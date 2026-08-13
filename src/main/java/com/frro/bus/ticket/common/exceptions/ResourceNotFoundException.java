package com.frro.bus.ticket.common.exceptions;

public class ResourceNotFoundException extends BusinessException {

    public ResourceNotFoundException(String resource, String field, Object value) {
        super(resource + " not found with " + field + ": " + value);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
