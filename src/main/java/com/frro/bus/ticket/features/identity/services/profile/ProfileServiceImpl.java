package com.frro.bus.ticket.features.identity.services.profile;

import org.springframework.stereotype.Service;

import com.frro.bus.ticket.common.exceptions.ResourceNotFoundException;
import com.frro.bus.ticket.features.identity.dtos.user.UpdateUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;
import com.frro.bus.ticket.features.identity.entities.User;
import com.frro.bus.ticket.features.identity.mappers.UserMapper;
import com.frro.bus.ticket.features.identity.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO update(UpdateUserDTO userRequest) {
        User existingUser = userRepository.findById(userRequest.id())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userRequest.id()));

        userRequest.firstName().ifPresent(existingUser::setFirstName);
        userRequest.lastName().ifPresent(existingUser::setLastName);
        userRequest.email().ifPresent(existingUser::setEmail);

        User savedUser = userRepository.save(existingUser);
        return userMapper.toUserDTO(savedUser);
    }

    @Override
    public UserDTO logicalDelete() {
        // TODO: Implement when having the session logic to identify the user to delete
        throw new UnsupportedOperationException("Unimplemented method 'logicalDelete'");
    }

    @Override
    public UserDTO delete() {
        // TODO: Implement when having the session logic to identify the user to delete
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
