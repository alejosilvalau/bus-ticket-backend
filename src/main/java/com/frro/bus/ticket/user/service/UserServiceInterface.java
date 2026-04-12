package com.frro.bus.ticket.user.service;

import com.frro.bus.ticket.user.dto.UserResponseInterface;
import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    List<UserResponseInterface> findAll();

    Optional<UserResponseInterface> findById(int id);

    Optional<UserResponseInterface> delete(int id);

    Optional<UserResponseInterface> save(int id);
}
