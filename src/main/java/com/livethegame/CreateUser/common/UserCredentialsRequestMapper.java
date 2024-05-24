package com.livethegame.CreateUser.common;

import com.livethegame.CreateUser.dto.UserCredentialsRequest;
import com.livethegame.CreateUser.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserCredentialsRequestMapper {
    User UserCredentialsRequestToUser(UserCredentialsRequest source);
}
