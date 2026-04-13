package com.frro.bus.ticket.user.service;

import com.frro.bus.ticket.user.dto.UserRequest;
import com.frro.bus.ticket.user.dto.UserResponse;
import com.frro.bus.ticket.user.mapper.UserMapper;
import com.frro.bus.ticket.user.model.User;
import com.frro.bus.ticket.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public Optional<UserResponse> findById(int id) {
        return userRepository.findById(id)
                .map(userMapper::toUserResponse);
    }

    @Override
    public Optional<UserResponse> delete(int id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return userMapper.toUserResponse(user);
                });
    }

    @Override
    public UserResponse create(UserRequest userRequest) {
        User user = userMapper.toUser(userRequest);
        user.setIsActive(true);
        user.setIsUser(true);
        user.setIsAdmin(false);

        // TODO: Hash the password here

        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    public Optional<UserResponse> update(int id, UserRequest userRequest) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(userRequest.firstName());
                    existingUser.setLastName(userRequest.lastName());
                    existingUser.setIsActive(userRequest.isActive());
                    existingUser.setEmail(userRequest.email());
                    existingUser.setEmail(userRequest.password());

                    User savedUser = userRepository.save(existingUser);
                    return userMapper.toUserResponse(savedUser);
                });
    }
}
