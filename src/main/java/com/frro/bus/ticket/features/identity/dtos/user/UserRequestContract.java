package com.frro.bus.ticket.user.dto;

import com.frro.bus.ticket.person.dto.PersonRequestContract;

public interface UserRequestContract extends PersonRequestContract {
    String email();

    String password();
}
