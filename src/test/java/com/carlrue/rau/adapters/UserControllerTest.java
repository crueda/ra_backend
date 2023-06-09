package com.carlrue.rau.adapters;

import com.carlrue.rau.adapters.out.persistence.UserEntity;
import com.carlrue.rau.adapters.out.persistence.UserMapper;
import com.carlrue.rau.common.exception.ResourceNotFoundException;
import com.carlrue.rau.domain.entities.User;
import com.carlrue.rau.domain.usecases.ReadUserService;
import com.carlrue.rau.domain.usecases.SaveUserService;
import com.carlrue.rau.adapters.in.api.UserController;
import com.carlrue.rau.ports.in.SaveUserCommand;
import com.carlrue.rau.ports.in.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureJsonTesters
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReadUserService readUserService;
    @MockBean
    private SaveUserService saveUserService;

    private List<UserEntity> expectedUserList;

    @Autowired
    private JacksonTester<UserEntity> jsonUser;

    @BeforeEach
    void setUp() {
        this.expectedUserList = new ArrayList<>();
        this.expectedUserList.add(new UserEntity(1L, "pedrocan", "Pedro de Candia", "pedrocan@sharedexpenses.com"));
        this.expectedUserList.add(new UserEntity(2L, "juanrol", "Juan Roldán", "juanrol@sharedexpenses.com"));
        this.expectedUserList.add(new UserEntity(3L, "crisper", "Cristobal de Peralta", "crisper@sharedexpenses.com"));
    }


    @Test
    void givenUserIdThenReturnsUserData() throws Exception {
        // Given
        long id = 1L;
        doReturn(UserMapper.entityToDomain(this.expectedUserList.get(0))).when(readUserService).read(id);

        // When
        MockHttpServletResponse response = mockMvc.perform(get("/api/user/{id}", id)
                    .accept(MediaType.APPLICATION_JSON)
                )
                .andReturn().getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(jsonUser.write(this.expectedUserList.get(0)).getJson(), response.getContentAsString());
    }



    @Test
    void givenNotExistingUserIdThenReturns404() throws Exception {
        long id = 4L;
        when(readUserService.read(id)).thenThrow(ResourceNotFoundException.class);

        MockHttpServletResponse response = mockMvc.perform(get("/api/user/{id}", id)
                    .accept(MediaType.APPLICATION_JSON)
                )
                .andReturn().getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void getAllUsersData() throws Exception {
        doReturn(this.expectedUserList).when(readUserService).readAll();

        MockHttpServletResponse response = mockMvc.perform(get("/api/users")
                    .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertTrue(response.getContentAsString().contains(jsonUser.write(this.expectedUserList.get(0)).getJson()));
        assertTrue(response.getContentAsString().contains(jsonUser.write(this.expectedUserList.get(1)).getJson()));
        assertTrue(response.getContentAsString().contains(jsonUser.write(this.expectedUserList.get(2)).getJson()));
    }

    @Test
    void createdNewUserReturnsTrue() throws Exception {
        UserEntity newUser = new UserEntity(4L, "andrescon", "Andrés Contero", "andrecon@gmail.com");
        SaveUserCommand command = new SaveUserCommand(null,
                newUser.getUsername(),
                newUser.getName(),
                newUser.getEmail());
        doReturn(true).when(saveUserService).save(command);

        MockHttpServletResponse response = mockMvc.perform(post("/api/user")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(jsonUser.write(newUser).getJson())
                )
                .andReturn().getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void givenUpdatedUserThenReturnsTrue() throws Exception {
        long id = 1L;
        UserEntity updatedUser = this.expectedUserList.get(0);
        updatedUser.setName("Francisco Moyano");
        SaveUserCommand command = new SaveUserCommand(id,
                updatedUser.getUsername(),
                updatedUser.getName(),
                updatedUser.getEmail());
        doReturn(true).when(saveUserService).save(command);

        MockHttpServletResponse response = mockMvc
                .perform(put("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonUser.write(updatedUser).getJson())
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
                .andReturn().getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


    @Test
    void whenDeleteUserReturnsOk() throws Exception {
        long id = 1L;
        doReturn(true).when(saveUserService).delete(id);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        MockHttpServletResponse response = mockMvc.perform(delete("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonUser.write(userEntity).getJson())
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andReturn().getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


    @Test
    void tryingToDeleteNotExistingUserIdThenReturns404() throws Exception {
        long id = 6L;
        when(saveUserService.delete(id)).thenThrow(ResourceNotFoundException.class);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        MockHttpServletResponse response = mockMvc.perform(delete("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonUser.write(userEntity).getJson())
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andReturn().getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }


}