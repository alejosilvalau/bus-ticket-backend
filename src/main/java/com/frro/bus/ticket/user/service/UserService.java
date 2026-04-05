package com.frro.bus.ticket.user.service;

import com.frro.bus.ticket.person.dto.PersonResponseInterface;
import com.frro.bus.ticket.user.dto.UserResponseInterface;
import com.frro.bus.ticket.user.mapper.UserMapper;
import com.frro.bus.ticket.user.model.User;
import com.frro.bus.ticket.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public List<PersonResponseInterface> findAll() {
    return userRepository.findAll().stream()
        .filter(user -> user instanceof User)
        .map(user -> (User) user)
        .map(userMapper::toUserResponse)
        .map(u -> (PersonResponseInterface) u)
        .toList();
  }

  @Override
  public List<PersonResponseInterface> findAllActive() {
    return userRepository.findAllByIsActiveTrue().stream()
        .filter(user -> user instanceof User)
        .map(user -> (User) user)
        .map(userMapper::toUserResponse)
        .map(u -> (PersonResponseInterface) u)
        .toList();
  }

  @Override
  public List<PersonResponseInterface> findAllInactive() {
    return userRepository.findAllByIsActiveFalse().stream()
        .filter(user -> user instanceof User)
        .map(user -> (User) user)
        .map(userMapper::toUserResponse)
        .map(u -> (PersonResponseInterface) u)
        .toList();
  }

  @Override
  public Optional<PersonResponseInterface> findById(String id) {
    return userRepository.findById(id)
        .filter(user -> user instanceof User)
        .map(user -> (User) user)
        .map(userMapper::toUserResponse)
        .map(u -> (PersonResponseInterface) u);
  }

  @Override
  public Optional<PersonResponseInterface> findByIdIfActive(String id) {
    return userRepository.findByIdAndIsActiveTrue(id)
        .filter(user -> user instanceof User)
        .map(user -> (User) user)
        .map(userMapper::toUserResponse)
        .map(u -> (PersonResponseInterface) u);
  }

  @Override
  public PersonResponseInterface activate(String id) {
    var person = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    User user = (User) person;
    user.setIsActive(true);
    userRepository.save(user);
    return userMapper.toUserResponse(user);
  }

  @Override
  public PersonResponseInterface deactivate(String id) {
    var person = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    User user = (User) person;
    user.setIsActive(false);
    userRepository.save(user);
    return userMapper.toUserResponse(user);
  }

  @Override
  public Long countActive() {
    return userRepository.countByIsActiveTrue();
  }

  @Override
  public Boolean existsById(String id) {
    return userRepository.existsById(id);
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
  public UserResponseInterface grantAdmin(String id) {
    var person = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    User user = (User) person;
    user.setIsAdmin(true);
    userRepository.save(user);
    return userMapper.toUserResponse(user);
  }

  @Override
  public UserResponseInterface revokeAdmin(String id) {
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

