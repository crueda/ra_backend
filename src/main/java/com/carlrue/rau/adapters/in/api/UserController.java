package com.carlrue.rau.adapters.in.api;

import com.carlrue.rau.ports.in.SaveUserCommand;
import com.carlrue.rau.ports.in.SaveUserPort;
import com.carlrue.rau.ports.in.ReadUserPort;
import com.carlrue.rau.adapters.out.persistence.UserEntity;
import com.carlrue.rau.adapters.out.persistence.UserMapper;
import com.carlrue.rau.ports.in.UserRequest;
import com.carlrue.rau.domain.entities.User;
import com.carlrue.rau.common.WebAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@RestController
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class UserController {

    private final SaveUserPort saveUserPort;
    private final ReadUserPort readUserPort;


    public UserController(SaveUserPort saveUserPort, ReadUserPort readUserPort) {

        this.saveUserPort = saveUserPort;
        this.readUserPort = readUserPort;
    }

    @PostMapping(path = "/api/user")
    @CrossOrigin(origins = "*")
    @ResponseStatus(code = HttpStatus.CREATED, reason = "CREATED")
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

    @PutMapping(path = "/api/user")
    @CrossOrigin(origins = "*")
    @ResponseStatus(code = HttpStatus.OK, reason = "UPDATED")
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

    @DeleteMapping(path = "/api/user")
    @CrossOrigin(origins = "*")
    @ResponseStatus(code = HttpStatus.OK, reason = "DELETED")
    void deleteUser(@RequestBody UserRequest userRequest) {

        Long id = (long)userRequest.getId();

        saveUserPort.delete(id);
    }

    @GetMapping("/api/user/{id}")
    @CrossOrigin(origins = "*")
    @ResponseStatus(code = HttpStatus.OK)
    public UserEntity readUser(@PathVariable Long id) {

        User user = readUserPort.read(id);
        return UserMapper.domainToEntity(user);
    }

    @GetMapping("/api/users")
    @CrossOrigin(origins = "*")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = readUserPort.readAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}
