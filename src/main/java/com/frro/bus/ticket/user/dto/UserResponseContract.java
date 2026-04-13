package com.frro.bus.ticket.user.dto;

import com.frro.bus.ticket.person.dto.PersonResponseContract;

public interface UserResponseContract extends PersonResponseContract {
    String email();

    Boolean isAdmin();
}
