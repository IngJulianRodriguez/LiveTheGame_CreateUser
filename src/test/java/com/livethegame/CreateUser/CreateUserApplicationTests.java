package com.livethegame.CreateUser;

import com.livethegame.CreateUser.controller.CreateUserRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CreateUserRestController.class)
@AutoConfigureMockMvc
class CreateUserApplicationTests {
    @Autowired
    private MockMvc mockMvc;


}
