package com.frro.bus.ticket.features.identity.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;
import com.frro.bus.ticket.common.utils.DataTypeMapperUtil;
import com.frro.bus.ticket.features.identity.dtos.user.CreateUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UpdateUserDTO;
import com.frro.bus.ticket.features.identity.entities.User;

@Mapper(componentModel = "spring", uses = { DataTypeMapperUtil.class })
public interface UserMapper {
    @Mapping(target = "isAdmin", source = "admin")
    @Mapping(target = "isActive", source = "active")
    UserDTO toUserDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", source = "isActive")
    @Mapping(target = "admin", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    User toUser(CreateUserDTO createUserDto);

    @Mapping(target = "firstName", source = "firstName", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "lastName", source = "lastName", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "email", source = "email", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "admin", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    User toUser(UpdateUserDTO updateUserDto);
}
