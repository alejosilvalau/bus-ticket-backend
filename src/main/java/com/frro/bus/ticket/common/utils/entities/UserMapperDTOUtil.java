package com.frro.bus.ticket.common.utils.entities;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.frro.bus.ticket.common.utils.EntityMapperDTOUtil;
import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;
import com.frro.bus.ticket.features.identity.entities.User;
import com.frro.bus.ticket.features.identity.mappers.UserMapper;

@Mapper(componentModel = "spring")
public abstract class UserMapperDTOUtil extends EntityMapperDTOUtil {

    @Autowired
    protected UserMapper userMapper;

    @Named("userToUserDTO")
    public UserDTO userToUserDTO(User user) {
        return mapSingle(user, userMapper::toUserDTO);
    }

    @Named("usersToUserDTOs")
    public List<UserDTO> usersToUserDTOs(List<User> users) {
        return mapList(users, userMapper::toUserDTO);
    }
}
