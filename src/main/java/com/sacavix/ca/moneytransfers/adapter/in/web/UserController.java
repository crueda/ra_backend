package com.sacavix.ca.moneytransfers.adapter.in.web;

import com.sacavix.ca.moneytransfers.application.port.in.SaveUserCommand;
import com.sacavix.ca.moneytransfers.application.port.in.SaveUserPort;
import com.sacavix.ca.moneytransfers.application.port.in.ReadUserPort;
import com.sacavix.ca.moneytransfers.adapter.out.persistence.UserEntity;
import com.sacavix.ca.moneytransfers.adapter.out.persistence.UserMapper;
import com.sacavix.ca.moneytransfers.domain.User;
import com.sacavix.ca.moneytransfers.common.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
public class UserController {

    private final SaveUserPort saveUserPort;
    private final ReadUserPort readUserPort;


    public UserController(SaveUserPort saveUserPort, ReadUserPort readUserPort) {

        this.saveUserPort = saveUserPort;
        this.readUserPort = readUserPort;
    }

    @PostMapping(path = "/user")
    void createUser(@RequestBody String userdata) {

        String username = new String("username1");
        String name = new String("name of user");
        String email = new String("user@domain.com");

        SaveUserCommand command = new SaveUserCommand(
                username,
                name,
                email);

        saveUserPort.save(command);
    }

    @GetMapping("/user/{username}")
    public UserEntity readUser(@PathVariable String username) {

        User user = readUserPort.read(username);
        return UserMapper.domainToEntity(user);
    }

}
