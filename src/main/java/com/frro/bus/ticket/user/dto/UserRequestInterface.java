package com.frro.bus.ticket.user.dto;

import com.frro.bus.ticket.person.dto.PersonRequestInterface;

public interface UserRequestInterface extends PersonRequestInterface {
    String email();

    String password();
}
