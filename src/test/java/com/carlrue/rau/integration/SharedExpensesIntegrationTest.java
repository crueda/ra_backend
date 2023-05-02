package com.carlrue.rau.integration;

import com.carlrue.rau.adapters.out.persistence.SpringUserRepository;
import com.carlrue.rau.adapters.out.persistence.UserPersistenceAdapter;
import com.carlrue.rau.domain.entities.User;
import com.carlrue.rau.ports.out.LoadUserPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class SharedExpensesIntegrationTest {

    private final SpringUserRepository userRepository;

    @Container
    public GenericContainer mysqldb = new GenericContainer(DockerImageName.parse("mysql:latest"))
            .withExposedPorts(3306);

    public SharedExpensesIntegrationTest(SpringUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Test
    public void givenValidCommonProduct_whenSave_thenPersist_andAssertThatExistsInDatabase(){

        LoadUserPort userPersistenceAdapter = new UserPersistenceAdapter(this.userRepository);
        User ue = userPersistenceAdapter.load(1L);

        Assertions.assertEquals(ue.getId(), 1L);
    }

}
