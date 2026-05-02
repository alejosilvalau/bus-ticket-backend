package com.frro.bus.ticket.features.identity.services.auth;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.frro.bus.ticket.features.identity.dtos.user.ChangePasswordUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.LoginUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;
import com.frro.bus.ticket.features.identity.entities.User;
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
    public boolean logout() {
        // TODO: Implement logout logic, e.g., invalidate session or token
        throw new UnsupportedOperationException("Unimplemented method 'logout'");
    }

    @Override
    public boolean changePassword(ChangePasswordUserDTO userRequest) {
        Optional<User> userFound = userRepository.findByEmailAndPassword(userRequest.email(),
                userRequest.password());

        if (userFound.isPresent()) {
            User userToUpdate = userFound.get();
            String newPassword = userRequest.newPassword();

            // TODO: Hash the new password before saving it to the database

            userToUpdate.setPassword(newPassword);
            userRepository.save(userToUpdate);
            return true;
        }
        return false;
    }
}
