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

  public List<PersonResponseInterface> findAll() {
    return userRepository.findAll().stream()
        .filter(user -> user instanceof User)
        .map(user -> (User) user)
        .map(userMapper::toUserResponse)
        .map(u -> (PersonResponseInterface) u)
        .toList();
  }

  public Optional<PersonResponseInterface> findById(int id) {
    return userRepository.findById(id)
        .filter(user -> user instanceof User)
        .map(user -> (User) user)
        .map(userMapper::toUserResponse)
        .map(u -> (PersonResponseInterface) u);
  }

  @Override
  public Optional<UserResponseInterface> findByEmail(String email) {
    return userRepository.findByEmail(email)
        .map(userMapper::toUserResponse)
        .map(u -> (UserResponseInterface) u);
  }

  @Override
  public List<UserResponseInterface> findAllAdmins() {
    return userRepository.findAllByIsAdminTrue().stream()
        .map(userMapper::toUserResponse)
        .map(u -> (UserResponseInterface) u)
        .toList();
  }

  @Override
  public List<UserResponseInterface> findAllNonAdmins() {
    return userRepository.findAllByIsAdminFalse().stream()
        .map(userMapper::toUserResponse)
        .map(u -> (UserResponseInterface) u)
        .toList();
  }

  @Override
  public UserResponseInterface grantAdmin(int id) {
    var person = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    User user = (User) person;
    user.setIsAdmin(true);
    userRepository.save(user);
    return userMapper.toUserResponse(user);
  }

  @Override
  public UserResponseInterface revokeAdmin(int id) {
    var person = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    User user = (User) person;
    user.setIsAdmin(false);
    userRepository.save(user);
    return userMapper.toUserResponse(user);
  }

  @Override
  public Long countAdmins() {
    return userRepository.countByIsAdminTrue();
  }
}
