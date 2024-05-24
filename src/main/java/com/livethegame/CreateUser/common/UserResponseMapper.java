package com.livethegame.CreateUser.common;

import com.livethegame.CreateUser.dto.UserResponse;
import com.livethegame.CreateUser.entities.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface UserResponseMapper {
    UserResponse UserToUserResponse(User source);
}
