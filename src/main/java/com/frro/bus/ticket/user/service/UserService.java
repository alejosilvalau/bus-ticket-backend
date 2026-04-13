package com.frro.bus.ticket.user.service;

import com.frro.bus.ticket.person.dto.PersonResponseInterface;
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
        Optional<User> userOptional = userRepository.findById(id)
                .filter(user -> user instanceof User);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.delete(user);
            return Optional.of(userMapper.toUserResponse(user));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserResponse> save(int id) {
        Optional<User> userOptional = userRepository.findById(id)
                .filter(user -> user instanceof User);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            User savedUser = userRepository.save(user);
            return Optional.of(userMapper.toUserResponse(savedUser));
        } else {
            return Optional.empty();
        }
    }

}
