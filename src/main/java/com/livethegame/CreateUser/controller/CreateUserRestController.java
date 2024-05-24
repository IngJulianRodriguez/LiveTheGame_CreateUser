package com.livethegame.CreateUser.controller;

import com.livethegame.CreateUser.dto.UserCredentialsRequest;
import com.livethegame.CreateUser.dto.UserResponse;
import com.livethegame.CreateUser.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Api Create User")
@RestController
@RequestMapping("/User")
public class CreateUserRestController {

    @Autowired
    UserService userService;

    @PostMapping("/Create")
    public ResponseEntity<?> createUser(@RequestBody UserCredentialsRequest input) {
        UserResponse userResponse = userService.CreateUser(input);
        return ResponseEntity.ok(userResponse);
    }

}
