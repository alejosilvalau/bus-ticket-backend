package com.frro.bus.ticket.features.identity.mappers;

import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;
import org.mapstruct.Mapping;

import com.frro.bus.ticket.features.identity.dtos.user.UserDTO;
import com.frro.bus.ticket.common.utils.OptionalMapperUtil;
import com.frro.bus.ticket.features.identity.dtos.user.CreateUserDTO;
import com.frro.bus.ticket.features.identity.dtos.user.UpdateUserDTO;
import com.frro.bus.ticket.features.identity.entities.User;

@Mapper(componentModel = "spring", uses = OptionalMapperUtil.class)
public interface UserMapper {

    @Mapping(target = "isAdmin", source = "getAdmin")
    @Mapping(target = "isActive", source = "getActive")
    UserDTO toUserDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", source = "isActive", qualifiedByName = "unwrapOptionalBoolean")
    @Mapping(target = "admin", source = "isAdmin", qualifiedByName = "unwrapOptionalBoolean")
    User toUser(CreateUserDTO createUserDto);

    @Mapping(target = "firstName", source = "firstName", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "lastName", source = "lastName", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "active", source = "isActive", qualifiedByName = "unwrapOptionalBoolean")
    @Mapping(target = "email", source = "email", qualifiedByName = "unwrapOptionalString")
    @Mapping(target = "admin", source = "isAdmin", qualifiedByName = "unwrapOptionalBoolean")
    @Mapping(target = "password", ignore = true)
    User toUser(UpdateUserDTO updateUserDto);
}
