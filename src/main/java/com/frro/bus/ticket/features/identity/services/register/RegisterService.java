package com.frro.bus.ticket.features.identity.services;

import java.util.Optional;

import com.frro.bus.ticket.features.identity.dtos.user.CreateUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;

public interface RegisterService {
    Optional<UserDTO> create(CreateUserDTO createUser);
}
