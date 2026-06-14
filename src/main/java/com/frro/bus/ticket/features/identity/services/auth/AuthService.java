package com.frro.bus.ticket.features.identity.services.auth;

import java.util.Optional;

import com.frro.bus.ticket.features.identity.dtos.user.ChangePasswordUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.LoginResponseDTO;
import com.frro.bus.ticket.features.identity.dtos.user.LoginUserDTO;

public interface AuthService {
    Optional<LoginResponseDTO> login(LoginUserDTO userRequest);

    boolean logout();

    boolean changePassword(ChangePasswordUserDTO userRequest);
}
