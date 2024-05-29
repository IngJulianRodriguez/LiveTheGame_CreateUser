package com.livethegame.CreateUser.controller;

import com.livethegame.CreateUser.dto.UserCredentialsRequest;
import com.livethegame.CreateUser.dto.UserResponse;
import com.livethegame.CreateUser.services.MonitoringService;
import com.livethegame.CreateUser.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Api Create User")
@RestController
@RequestMapping("/users")
public class CreateUserRestController {

    @Autowired
    UserService userService;
    @Autowired
    MonitoringService monitoringService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserCredentialsRequest input) {
        String message = "";
        try {
            if (input.isValidData()) {
                if(userService.isTheEmailRegistered(input.getEmail())){
                    message = "Correo ya se encuentra registrado";
                    monitoringService.registerControlledExceptionLog("",input.toString()+" "+message);
                    return ResponseEntity.badRequest().body(message);
                }else {
                    UserResponse userResponse = userService.createUser(input);
                    monitoringService.registerSuccessLog(String.valueOf(userResponse.getId()),input.toString());
                    return ResponseEntity.ok(userResponse);
                }
            } else {
                message = "Datos de usuario no válidos";
                monitoringService.registerControlledExceptionLog("",input.toString()+" "+message);
                return ResponseEntity.badRequest().body(message);
            }
        } catch (Exception e) {
            monitoringService.registerNotControlledExceptionLog("",input.toString()+" "+e.toString());
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
    @GetMapping("/test-create")
    public ResponseEntity<?> testCreateUser(){
        return ResponseEntity.ok().build();
    }
}
