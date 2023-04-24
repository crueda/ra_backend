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

        User target = loadUserPort.load(command.getUsername());

        updateUserPort.update(target);

        return true;
    }
}
