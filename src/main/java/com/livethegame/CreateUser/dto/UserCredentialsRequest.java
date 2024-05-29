package com.livethegame.CreateUser.dto;

import com.livethegame.CreateUser.Utils.Validator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

@ApiModel()
public class UserCredentialsRequest {

    @ApiModelProperty(name = "email", required = true,example = "ejemplo@correo.com", value = "")
    private String email;

    @ApiModelProperty(name = "password", required = true,example = "", value = "")
    private String password;


    public UserCredentialsRequest(){
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidData(){
        return Validator.validate(getEmail())
            && !getPassword().isEmpty();
    }
}
