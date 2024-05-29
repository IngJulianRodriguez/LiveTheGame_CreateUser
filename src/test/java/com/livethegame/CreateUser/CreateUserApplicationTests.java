package com.livethegame.CreateUser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.livethegame.CreateUser.common.UserResponseMapper;
import com.livethegame.CreateUser.controller.CreateUserRestController;
import com.livethegame.CreateUser.dto.UserCredentialsRequest;
import com.livethegame.CreateUser.dto.UserResponse;
import com.livethegame.CreateUser.repository.UserRepository;
import com.livethegame.CreateUser.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Base64;

@WebMvcTest(CreateUserRestController.class)
@AutoConfigureMockMvc
class CreateUserApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;
    @MockBean
    private UserResponseMapper userResponseMapper;

    private static final String PASSWORD = "admin";
    private static final String USER = "admin";

    @Test
    public void testCreateUser_Success() throws Exception {
        UserCredentialsRequest request = new UserCredentialsRequest();
        request.setEmail("ejemplo@correo.com");
        request.setPassword("password");

        UserResponse response = new UserResponse();
        response.setId(1L);
        response.setEmail("ejemplo@correo.com");
        Mockito.when(userService.createUser(request)).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testCreateUser_Business_PasswordEmpty() throws Exception {
        UserCredentialsRequest request = new UserCredentialsRequest();
        request.setEmail("ejemplo@correo.com");
        request.setPassword("");
        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void testCreateUser_Business_EmailInvalid() throws Exception {
        UserCredentialsRequest request = new UserCredentialsRequest();
        request.setEmail("ejemplocorreo.com");
        request.setPassword("password");
        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void testCreateUser_Business_EmailRegistered() throws Exception {
        UserCredentialsRequest request = new UserCredentialsRequest();
        request.setEmail("ejemplotest@correo.com");
        request.setPassword("password");
        Mockito.when(userService.isTheEmailRegistered(request.getEmail())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void testCreateUser_Business_WithoutAuthorization() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/create")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void testCreateUser_Business_testUnauthorizedAccess() throws Exception {
        Base64.Encoder encoder = Base64.getEncoder();
        String encodingParaUsuarioSinPermiso = encoder.encodeToString(("usuario" + ":" + "password").getBytes());
        mockMvc.perform(MockMvcRequestBuilders.get("/users/create")
                        .header("Authorization", "Basic " + encodingParaUsuarioSinPermiso)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    private String asJsonString(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}
