package com.frro.bus.ticket.user.service;

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
    public UserResponse save(User user) {
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }
}
