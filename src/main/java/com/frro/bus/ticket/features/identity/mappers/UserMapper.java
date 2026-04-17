package com.frro.bus.ticket.features.identity.mappers;

import org.mapstruct.Mapper;

import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.CreateUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UpdateUserDTO;
import com.frro.bus.ticket.features.identity.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);

    User toUser(CreateUserDTO createUserDto);

    User toUser(UpdateUserDTO updateUserDto);
}
