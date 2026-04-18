package com.frro.bus.ticket.features.identity.services;

import java.util.Optional;

import com.frro.bus.ticket.features.identity.dtos.user.UpdateUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;

public interface ProfileService {
    Optional<UserDTO> update(UpdateUserDTO userRequest);

    Optional<UserDTO> delete();

    Optional<UserDTO> logicalDelete();
}
