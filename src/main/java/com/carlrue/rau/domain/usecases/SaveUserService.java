package com.carlrue.rau.domain.usecases;

import com.carlrue.rau.common.exception.ResourceInvalidException;
import com.carlrue.rau.domain.entities.User;
import com.carlrue.rau.ports.in.SaveUserCommand;
import com.carlrue.rau.ports.in.SaveUserPort;
import com.carlrue.rau.ports.out.LoadUserPort;
import com.carlrue.rau.ports.out.UpdateUserPort;
import com.carlrue.rau.common.UseCase;

import javax.transaction.Transactional;

@UseCase
public class SaveUserService implements SaveUserPort {

    private final LoadUserPort loadUserPort;
    private final UpdateUserPort updateUserPort;

    public SaveUserService(LoadUserPort loadUserPort, UpdateUserPort updateUserPort) {
        this.loadUserPort = loadUserPort;
        this.updateUserPort = updateUserPort;
    }

    @Transactional
    @Override
    public boolean save(SaveUserCommand command) {

        User user = new User();
        user.setUsername(command.getUsername());
        user.setName(command.getName());
        user.setEmail(command.getEmail());

        if (!user.usernameIsValid()) {
            throw new ResourceInvalidException("User", "Username", user.getUsername());
        }
        updateUserPort.save(user);

        return true;
    }

    @Transactional
    @Override
    public boolean update(SaveUserCommand command) {

        User user = new User();
        user.setId(command.getId());
        user.setUsername(command.getUsername());
        user.setName(command.getName());
        user.setEmail(command.getEmail());

        if (!user.usernameIsValid()) {
            throw new ResourceInvalidException("User", "Username", user.getUsername());
        }
        updateUserPort.update(user);

        return true;
    }

    @Transactional
    @Override
    public boolean delete(Long id) {

        updateUserPort.delete(id);

        return true;
    }

}
