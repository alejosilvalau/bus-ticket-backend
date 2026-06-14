package com.frro.bus.ticket.common.exceptions;

public class DuplicateResourceException extends BusinessException {

    public DuplicateResourceException(String resource, String field, Object value) {
        super(resource + " already exists with " + field + ": " + value);
    }

    public DuplicateResourceException(String message) {
        super(message);
    }
}
