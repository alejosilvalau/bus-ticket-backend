package com.frro.bus.ticket.person.dto;

public record PersonResponse(
    String id,
    String firstName,
    String lastName,
    Boolean isActive
) implements PersonResponseInterface {
}
