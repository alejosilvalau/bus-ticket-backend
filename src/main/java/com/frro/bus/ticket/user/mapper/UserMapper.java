package com.frro.bus.ticket.user.mapper;

import com.frro.bus.ticket.user.dto.UserResponse;
import com.frro.bus.ticket.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);
}
