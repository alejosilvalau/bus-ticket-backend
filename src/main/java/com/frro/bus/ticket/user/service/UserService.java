package com.frro.bus.ticket.user.service;

import com.frro.bus.ticket.user.dto.UserRequest;
import com.frro.bus.ticket.user.dto.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponse> findAll();

    Optional<UserResponse> findById(int id);

    UserResponse create(UserRequest userRequest);

    Optional<UserResponse> delete(int id);

    Optional<UserResponse> update(int id, UserRequest userRequest);
}
