package com.frro.bus.ticket.user.service;

import com.frro.bus.ticket.person.dto.PersonResponseInterface;
import com.frro.bus.ticket.user.dto.UserResponseInterface;
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
    public List<UserResponseInterface> findAll() {
        return userRepository.findAll().stream()
                .filter(user -> user instanceof User)
                .map(user -> (User) user)
                .map(userMapper::toUserResponse)
                .map(u -> (UserResponseInterface) u)
                .toList();
    }

    @Override
    public Optional<UserResponseInterface> findById(int id) {
        return userRepository.findById(id)
                .filter(user -> user instanceof User)
                .map(user -> (User) user)
                .map(userMapper::toUserResponse)
                .map(u -> (UserResponseInterface) u);
    }

    @Override
    public Optional<UserResponseInterface> delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Optional<UserResponseInterface> save(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
