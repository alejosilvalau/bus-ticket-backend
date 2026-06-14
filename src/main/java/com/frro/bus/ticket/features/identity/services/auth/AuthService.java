package com.frro.bus.ticket.features.identity.services.auth;

import com.frro.bus.ticket.features.identity.dtos.user.ChangePasswordUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.LoginResponseDTO;
import com.frro.bus.ticket.features.identity.dtos.user.LoginUserDTO;

public interface AuthService {
    LoginResponseDTO login(LoginUserDTO userRequest);

    boolean logout();

    boolean changePassword(ChangePasswordUserDTO userRequest);
}
