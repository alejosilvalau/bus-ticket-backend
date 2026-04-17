package com.frro.bus.ticket.features.identity.dtos.user;

import com.frro.bus.ticket.features.identity.dtos.person.PersonContract;

public interface UserContract extends PersonContract {
    String email();

    String password();

    Boolean isAdmin();
}
