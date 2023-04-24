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
    public User read(Long id) {
        User user = loadUserPort.load(id);

        return user;
    }
}
