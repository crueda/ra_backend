package com.carlrue.rau.domain.usecases;


import com.carlrue.rau.common.exception.ResourceNotFoundException;
import com.carlrue.rau.domain.entities.User;
import com.carlrue.rau.domain.usecases.SaveUserService;
import com.carlrue.rau.ports.in.SaveUserCommand;
import com.carlrue.rau.ports.out.LoadUserPort;
import com.carlrue.rau.ports.out.UpdateUserPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveUserServiceTest {

    @Mock
    private LoadUserPort loadUserPort;

    @Mock
    private UpdateUserPort updateUserPort;

    private SaveUserService saveUserService;
    private List<User> expectedUserList;

    @BeforeEach
    void setUp() {
        this.saveUserService = new SaveUserService(loadUserPort, updateUserPort);
        this.expectedUserList = new ArrayList<>();
        this.expectedUserList.add(new User(1L, "pedrocan", "Pedro de Candia", "pedrocan@sharedexpenses.com"));
        this.expectedUserList.add(new User(2L, "juanrol", "Juan Roldán", "juanrol@sharedexpenses.com"));
        this.expectedUserList.add(new User(3L, "crisper", "Cristobal de Peralta", "crisper@sharedexpenses.com"));
    }

    @Test
    void createUserWhenUserIsProvided() {
        User expectedNewUser = new User(null, "ines", "Ines Alonso", "ines@sharedexpenses.com");
        SaveUserCommand command = new SaveUserCommand(null, "ines", "Ines Alonso", "ines@sharedexpenses.com");
        doReturn(true).when(updateUserPort).save(expectedNewUser);

        boolean result = saveUserService.save(command);

        assertEquals(result, true);
    }


    @Test
    void updateUserWhenIsEdited() {
        User expectedUpdateUser = new User(1L, "ines", "Ines Alonso", "ines@sharedexpenses.com");
        SaveUserCommand command = new SaveUserCommand(1L, "ines", "Ines Alonso", "ines@sharedexpenses.com");
        doReturn(true).when(updateUserPort).update(expectedUpdateUser);

        boolean result = saveUserService.update(command);

        assertEquals(result, true);
    }


    @Test
    void tryingToUpdateNotExistingUserIdThenReturnsResourceNotFoundException() {
        User expectedUpdateUser = this.expectedUserList.get(0);
        SaveUserCommand command = new SaveUserCommand(expectedUpdateUser.getId(), expectedUpdateUser.getUsername(), expectedUpdateUser.getName(), expectedUpdateUser.getEmail());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> when(saveUserService.update(command)),
                "ResourceNotFoundException was expected"
        );

        assertTrue(exception.getMessage().contains("User not found with Id: '1'"));
    }


    @Test
    void deleteUserById() {
        User optExpectedUser = expectedUserList.get(0);
        doReturn(optExpectedUser).when(loadUserPort).load(1L);

        assertFalse(saveUserService.delete(1L));
    }

    @Test
    void tryingToDeleteNotExistingUserIdThenReturnsResourceNotFoundException() {
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> when(saveUserService.delete(1L)),
                "ResourceNotFoundException was expected"
        );

        assertTrue(exception.getMessage().contains("User not found with Id: '1'"));
    }

}
