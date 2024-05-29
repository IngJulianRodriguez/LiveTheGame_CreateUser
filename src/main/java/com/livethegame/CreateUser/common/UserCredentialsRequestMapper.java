package com.livethegame.CreateUser.common;

import com.livethegame.CreateUser.dto.UserCredentialsRequest;
import com.livethegame.CreateUser.entities.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserCredentialsRequestMapper {
    Users UserCredentialsRequestToUser(UserCredentialsRequest source);
}
