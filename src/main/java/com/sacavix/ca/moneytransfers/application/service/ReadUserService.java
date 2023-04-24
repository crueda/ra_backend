package com.sacavix.ca.moneytransfers.application.service;

import com.sacavix.ca.moneytransfers.application.port.in.ReadUserPort;
import com.sacavix.ca.moneytransfers.application.port.out.LoadUserPort;
import com.sacavix.ca.moneytransfers.common.UseCase;
import com.sacavix.ca.moneytransfers.domain.User;

import javax.transaction.Transactional;

@UseCase
public class ReadUserService implements ReadUserPort {

    private final LoadUserPort loadUserPort;

    public ReadUserService(LoadUserPort loadUserPort) {
        this.loadUserPort = loadUserPort;
    }

    @Transactional
    @Override
    public User read(String username) {
        System.out.println(username);
        User user = loadUserPort.load(username);
        System.out.println(user.getUsername());

        return user;
    }
}
