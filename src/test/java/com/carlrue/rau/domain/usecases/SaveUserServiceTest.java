package com.carlrue.rau.domain.usecases;


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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

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
        User expectedNewUser = new User(4L, "ines", "Ines Alonso", "ines@sharedexpenses.com");
        SaveUserCommand command = new SaveUserCommand(4L, "ines", "Ines Alonso", "ines@sharedexpenses.com");
        //doReturn((expectedNewUser)).when(updateUserPort).save(expectedNewUser);

        boolean result = saveUserService.save(command);

        assertEquals(result, true);
    }

    /*
    @Test
    void updateUserWhenIsEdited() {
        Optional<User> optExpectedUser = Optional.ofNullable(expectedUserList.get(0));
        doReturn(optExpectedUser).when(userRepository).findById(1L);
        User expectedUpdatedUser = this.expectedUserList.get(0);
        expectedUpdatedUser.setName("Pablo Álvarez");
        doReturn(expectedUpdatedUser).when(userRepository).save(any());

        User user = userService.updateUser(expectedUpdatedUser, 1L);

        assertNotNull(user);
        assertEquals(expectedUpdatedUser, user);
    }

    @Test
    void tryingToUpdateNotExistingUserIdThenReturnsResourceNotFoundException() {
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> when(userService.updateUser(this.expectedUserList.get(0), 1L)),
                "ResourceNotFoundException was expected"
        );

        assertTrue(exception.getMessage().contains("User not found with Id: '1'"));
    }

    @Test
    void deleteUserById() {
        Optional<User> optExpectedUser = Optional.ofNullable(expectedUserList.get(0));
        doReturn(optExpectedUser).when(userRepository).findById(1L);

        assertNull(userService.deleteUser(1L));
    }

    @Test
    void tryingToDeleteNotExistingUserIdThenReturnsResourceNotFoundException() {
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> when(userService.deleteUser(1L)),
                "ResourceNotFoundException was expected"
        );

        assertTrue(exception.getMessage().contains("User not found with Id: '1'"));
    }


     */
}
