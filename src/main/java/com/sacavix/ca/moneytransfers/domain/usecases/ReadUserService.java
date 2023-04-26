package com.sacavix.ca.moneytransfers.domain.usecases;

import com.sacavix.ca.moneytransfers.domain.entities.User;
import com.sacavix.ca.moneytransfers.ports.in.ReadUserPort;
import com.sacavix.ca.moneytransfers.ports.out.LoadUserPort;
import com.sacavix.ca.moneytransfers.common.UseCase;

import javax.transaction.Transactional;
import java.util.List;

@UseCase
public class ReadUserService implements ReadUserPort {

    private final LoadUserPort loadUserPort;

    public ReadUserService(LoadUserPort loadUserPort) {
        this.loadUserPort = loadUserPort;
    }

    @Transactional
    @Override
    public User read(Long id) {
        User user = loadUserPort.load(id);

        return user;
    }

    @Transactional
    @Override
    public List<User> readAll() {
        List<User> users = loadUserPort.loadAll();

        return users;
    }

}
