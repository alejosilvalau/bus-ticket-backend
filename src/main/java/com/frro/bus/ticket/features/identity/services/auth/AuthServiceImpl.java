package com.frro.bus.ticket.features.identity.services.auth;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.frro.bus.ticket.features.identity.dtos.user.ChangePasswordUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.LoginUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;
import com.frro.bus.ticket.features.identity.mappers.UserMapper;
import com.frro.bus.ticket.features.identity.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<UserDTO> login(LoginUserDTO userRequest) {
        return userRepository.findByEmailAndPassword(userRequest.email(), userRequest.password())
                .map(userMapper::toUserDTO);
    }

    @Override
    public Boolean logout() {
        // TODO: Implement logout logic, e.g., invalidate session or token
        throw new UnsupportedOperationException("Unimplemented method 'logout'");
    }

    @Override
    public Boolean changePassword(ChangePasswordUserDTO userRequest) {
        // TODO: Implement change password logic, e.g., verify old password, update to
        // new password hashing it before saving
        throw new UnsupportedOperationException("Unimplemented method 'changePassword'");
    }
}
