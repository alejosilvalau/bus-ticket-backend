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
                    existingUser.setFirstName(userRequest.firstName());
                    existingUser.setLastName(userRequest.lastName());
                    existingUser.setIsActive(userRequest.isActive());
                    existingUser.setEmail(userRequest.email());

                    User savedUser = userRepository.save(existingUser);
                    return userMapper.toUserDTO(savedUser);
                });
    }

    @Override
    public Optional<UserDTO> delete() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Optional<UserDTO> logicalDelete() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logicalDelete'");
    }

}
