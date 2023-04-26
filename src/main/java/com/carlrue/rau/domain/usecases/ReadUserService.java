package com.carlrue.rau.domain.usecases;

import com.carlrue.rau.domain.entities.User;
import com.carlrue.rau.ports.in.ReadUserPort;
import com.carlrue.rau.ports.out.LoadUserPort;
import com.carlrue.rau.common.UseCase;

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
