package com.livethegame.CreateUser.common;

import com.livethegame.CreateUser.dto.UserResponse;
import com.livethegame.CreateUser.entities.Users;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserResponseMapper {
    UserResponse UserToUserResponse(Users source);
}
