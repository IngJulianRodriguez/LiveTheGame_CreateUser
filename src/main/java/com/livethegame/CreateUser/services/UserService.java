package com.livethegame.CreateUser.services;

import com.livethegame.CreateUser.common.UserCredentialsRequestMapper;
import com.livethegame.CreateUser.common.UserResponseMapper;
import com.livethegame.CreateUser.dto.UserCredentialsRequest;
import com.livethegame.CreateUser.dto.UserResponse;
import com.livethegame.CreateUser.entities.Users;
import com.livethegame.CreateUser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserResponseMapper userResponseMapper;
    @Autowired
    UserCredentialsRequestMapper userCredentialsRequestMapper;

    public UserResponse createUser(UserCredentialsRequest userCredentialsRequest){
        Users userCredentialsRequestToUsers = userCredentialsRequestMapper.UserCredentialsRequestToUser(userCredentialsRequest);
        Users save = userRepository.save(userCredentialsRequestToUsers);
        UserResponse UserToUserResponse = userResponseMapper.UserToUserResponse(save);
        return UserToUserResponse;
    }
    public boolean isTheEmailRegistered(String email){
        try {
            Users users = userRepository.findByEmail(email);
            return users != null;
        } catch (Exception e) {
            return false;
        }
    }

}
