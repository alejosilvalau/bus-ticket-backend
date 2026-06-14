package com.frro.bus.ticket.features.identity.services.profile;

import com.frro.bus.ticket.features.identity.dtos.user.UpdateUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;

public interface ProfileService {
    UserDTO update(UpdateUserDTO userRequest);

    UserDTO logicalDelete();

    UserDTO delete();
}
