package com.frro.bus.ticket.features.identity.services.profile;

import org.springframework.stereotype.Service;

import com.frro.bus.ticket.common.exceptions.DuplicateResourceException;
import com.frro.bus.ticket.common.exceptions.ResourceNotFoundException;
import com.frro.bus.ticket.common.security.JwtUtil;
import com.frro.bus.ticket.features.identity.dtos.user.UpdateUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;
import com.frro.bus.ticket.features.identity.entities.User;
import com.frro.bus.ticket.features.identity.mappers.UserMapper;
import com.frro.bus.ticket.features.identity.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final HttpServletRequest request;

    @Override
    public UserDTO update(UpdateUserDTO userRequest) {
        User existingUser = userRepository.findById(userRequest.id())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userRequest.id()));

        userRequest.email().ifPresent(newEmail -> {
            userRepository.findByEmail(newEmail)
                    .filter(found -> found.getId() != userRequest.id())
                    .ifPresent(user -> {
                        throw new DuplicateResourceException("User", "email", newEmail);
                    });
        });
        userRequest.email().ifPresent(existingUser::setEmail);

        userRequest.firstName().ifPresent(existingUser::setFirstName);
        userRequest.lastName().ifPresent(existingUser::setLastName);

        User savedUser = userRepository.save(existingUser);
        return userMapper.toUserDTO(savedUser);
    }

    @Override
    public UserDTO logicalDelete() {
        User user = getAuthenticatedUser();
        user.setActive(false);
        User savedUser = userRepository.save(user);
        return userMapper.toUserDTO(savedUser);
    }

    @Override
    public UserDTO delete() {
        User user = getAuthenticatedUser();
        UserDTO userDTO = userMapper.toUserDTO(user);
        userRepository.deleteById(user.getId());
        return userDTO;
    }

    private User getAuthenticatedUser() {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        int userId = jwtUtil.extractUserId(token);
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }
}
