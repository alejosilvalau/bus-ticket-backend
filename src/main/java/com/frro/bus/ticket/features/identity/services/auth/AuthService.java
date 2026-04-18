package com.frro.bus.ticket.features.identity.services;

import java.util.Optional;

import com.frro.bus.ticket.features.identity.dtos.user.ChangePasswordUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.LoginUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;

public interface AuthService {
    Optional<UserDTO> login(LoginUserDTO userRequest);

    Boolean logout();

    Boolean changePassword(ChangePasswordUserDTO userRequest);
}
