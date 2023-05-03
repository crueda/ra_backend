package com.carlrue.rau.domain.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserTest {

    @Test
    public void test_givenUsername_isValid(){
        User user = new User(1L, "pedrocan", "Pedro de Candia", "pedrocan@sharedexpenses.com");

        assertTrue(user.usernameIsValid());
    }
    @Test
    public void test_givenUsername_isNotValid(){
        User user = new User(1L, "ped", "Pedro de Candia", "pedrocan@sharedexpenses.com");

        assertFalse(user.usernameIsValid());
    }
}
