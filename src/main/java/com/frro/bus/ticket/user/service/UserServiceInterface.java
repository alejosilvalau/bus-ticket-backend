package com.frro.bus.ticket.user.service;

import com.frro.bus.ticket.user.dto.UserResponse;
import com.frro.bus.ticket.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    List<UserResponse> findAll();

    Optional<UserResponse> findById(int id);

    Optional<UserResponse> delete(int id);

    UserResponse save(User user);
}
