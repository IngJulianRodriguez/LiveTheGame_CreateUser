package com.livethegame.CreateUser.services;

import com.livethegame.CreateUser.common.UserCredentialsRequestMapper;
import com.livethegame.CreateUser.common.UserResponseMapper;
import com.livethegame.CreateUser.dto.UserCredentialsRequest;
import com.livethegame.CreateUser.dto.UserResponse;
import com.livethegame.CreateUser.entities.User;
import com.livethegame.CreateUser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserResponseMapper userResponseMapper;
    @Autowired
    UserCredentialsRequestMapper userCredentialsRequestMapper;

    public UserResponse CreateUser(UserCredentialsRequest userCredentialsRequest){
        User UserCredentialsRequestToUser = userCredentialsRequestMapper.UserCredentialsRequestToUser(userCredentialsRequest);
        User save = userRepository.save(UserCredentialsRequestToUser);
        UserResponse UserToUserResponse = userResponseMapper.UserToUserResponse(save);
        return UserToUserResponse;
    }
}
