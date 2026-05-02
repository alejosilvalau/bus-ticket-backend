package com.frro.bus.ticket.features.identity.services.register;

import org.springframework.stereotype.Service;

import com.frro.bus.ticket.features.identity.dtos.user.CreateUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;
import com.frro.bus.ticket.features.identity.entities.User;
import com.frro.bus.ticket.features.identity.mappers.UserMapper;
import com.frro.bus.ticket.features.identity.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO create(CreateUserDTO userRequest) {
        User user = userMapper.toUser(userRequest);

        // TODO: Hash the password here
        // TODO: Check if the user already exists but is inactive

        User savedUser = userRepository.save(user);
        return userMapper.toUserDTO(savedUser);
    }
}
