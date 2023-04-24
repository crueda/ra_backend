package com.sacavix.ca.moneytransfers.application.service;

import com.sacavix.ca.moneytransfers.application.port.in.SaveUserCommand;
import com.sacavix.ca.moneytransfers.application.port.in.SaveUserPort;
import com.sacavix.ca.moneytransfers.application.port.out.LoadUserPort;
import com.sacavix.ca.moneytransfers.application.port.out.UpdateUserPort;
import com.sacavix.ca.moneytransfers.common.UseCase;
import com.sacavix.ca.moneytransfers.domain.User;

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

        //long id = 3L;

        //User target = loadUserPort.load(id);
        User user = new User();
        user.setUsername(command.getUsername());
        user.setName(command.getName());
        user.setEmail(command.getEmail());

        updateUserPort.save(user);

        return true;
    }

    @Transactional
    @Override
    public boolean update(SaveUserCommand command) {

        long id = 3L;
        User target = loadUserPort.load(id);

        updateUserPort.update(target);

        return true;
    }
}
