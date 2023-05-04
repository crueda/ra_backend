package com.carlrue.rau.domain.usecases;


import com.carlrue.rau.common.exception.ResourceNotFoundException;
import com.carlrue.rau.domain.entities.User;
import com.carlrue.rau.domain.usecases.ReadUserService;
import com.carlrue.rau.ports.out.LoadUserPort;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReadUserServiceTest {

    @Mock
    private LoadUserPort loadUserPort;

    private ReadUserService readUserService;
    private List<User> expectedUserList;

    @BeforeEach
    void setUp() {
        this.readUserService = new ReadUserService(loadUserPort);
        this.expectedUserList = new ArrayList<>();
        this.expectedUserList.add(new User(1L, "pedrocan", "Pedro de Candia", "pedrocan@sharedexpenses.com"));
        this.expectedUserList.add(new User(2L, "juanrol", "Juan Roldán", "juanrol@sharedexpenses.com"));
        this.expectedUserList.add(new User(3L, "crisper", "Cristobal de Peralta", "crisper@sharedexpenses.com"));
    }
    @Test
    void givenUserIdThenReturnsUser() {
        // Given
        User optExpectedUser = expectedUserList.get(0);
        doReturn(optExpectedUser).when(loadUserPort).load(1L);

        // When
        User user = readUserService.read(1L);

        // Then
        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("Pedro de Candia", user.getName());
    }


    @Test
    void givenNotExistingUserIdThenReturnsResourceNotFoundException() {
        long id = 6L;
        when(loadUserPort.load(id)).thenThrow(ResourceNotFoundException.class);

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> when(readUserService.read(id)),
                "ResourceNotFoundException was expected"
        );
    }

    @Test
    void getAllUsers() {
        doReturn(this.expectedUserList).when(loadUserPort).loadAll();
        // When
        List<User> userList = readUserService.readAll();
        // Then
        assertNotNull(userList);
        assertEquals(userList.size(), 3);
        assertEquals(1L, userList.get(0).getId());
        assertEquals(2L, userList.get(1).getId());
        assertEquals(3L, userList.get(2).getId());
    }

    @Test
    void getEmptyListWhenThereIsNoUsers() {
        List<User> expectedUserList = new ArrayList<>();
        doReturn(expectedUserList).when(loadUserPort).loadAll();

        // When
        List<User> userList = loadUserPort.loadAll();

        // Then
        assertNotNull(userList);
        assertTrue(userList.isEmpty());
    }

}
