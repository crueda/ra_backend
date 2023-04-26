package com.carlrue.rau;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class MoneytransfersIntegrationTest {

    @Container
    public GenericContainer mysqldb = new GenericContainer(DockerImageName.parse("mysql:latest"))
            .withExposedPorts(3306);




}
