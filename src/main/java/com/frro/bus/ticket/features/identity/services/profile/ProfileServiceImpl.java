package com.frro.bus.ticket.features.identity.services.profile;

import java.util.Optional;

import org.springframework.stereotype.Service;

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
    public Optional<UserDTO> update(UpdateUserDTO userRequest) {
        return userRepository.findById(userRequest.id())
                .map(existingUser -> {
                    userRequest.firstName().ifPresent(existingUser::setFirstName);
                    userRequest.lastName().ifPresent(existingUser::setLastName);
                    userRequest.isActive().ifPresent(existingUser::setActive);
                    userRequest.email().ifPresent(existingUser::setEmail);
                    userRequest.isAdmin().ifPresent(existingUser::setAdmin);

                    User savedUser = userRepository.save(existingUser);
                    return userMapper.toUserDTO(savedUser);
                });
    }

    @Override
    public Optional<UserDTO> delete() {
        // TODO: Implement when having the session logic to identify the user to delete
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Optional<UserDTO> logicalDelete() {
        // TODO: Implement when having the session logic to identify the user to delete
        throw new UnsupportedOperationException("Unimplemented method 'logicalDelete'");
    }

}
