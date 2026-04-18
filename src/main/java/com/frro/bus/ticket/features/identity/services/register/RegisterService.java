package com.frro.bus.ticket.features.identity.services.register;

import com.frro.bus.ticket.features.identity.dtos.user.CreateUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;

public interface RegisterService {
    UserDTO create(CreateUserDTO userRequest);
}
