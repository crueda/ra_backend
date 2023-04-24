package com.sacavix.ca.moneytransfers.adapter.in.web;

import com.sacavix.ca.moneytransfers.application.port.in.SaveUserCommand;
import com.sacavix.ca.moneytransfers.application.port.in.SaveUserPort;
import com.sacavix.ca.moneytransfers.application.port.in.ReadUserPort;
import com.sacavix.ca.moneytransfers.adapter.out.persistence.UserEntity;
import com.sacavix.ca.moneytransfers.adapter.out.persistence.UserMapper;
import com.sacavix.ca.moneytransfers.application.port.in.UserRequest;
import com.sacavix.ca.moneytransfers.domain.User;
import com.sacavix.ca.moneytransfers.common.WebAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    void createUser(@RequestBody UserRequest userRequest) {

        String username = new String(userRequest.getUsername());
        String name = new String(userRequest.getName());
        String email = new String(userRequest.getEmail());

        SaveUserCommand command = new SaveUserCommand(null,
                username,
                name,
                email);

        saveUserPort.save(command);
    }

    @PutMapping(path = "/user")
    void updateUser(@RequestBody UserRequest userRequest) {

        Long id = (long)userRequest.getId();
        String username = new String(userRequest.getUsername());
        String name = new String(userRequest.getName());
        String email = new String(userRequest.getEmail());

        SaveUserCommand command = new SaveUserCommand(id,
                username,
                name,
                email);

        saveUserPort.update(command);
    }

    @DeleteMapping(path = "/user")
    void deleteUser(@RequestBody UserRequest userRequest) {

        Long id = (long)userRequest.getId();

        saveUserPort.delete(id);
    }

    @GetMapping("/user/{id}")
    public UserEntity readUser(@PathVariable Long id) {

        User user = readUserPort.read(id);
        return UserMapper.domainToEntity(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = readUserPort.readAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}
