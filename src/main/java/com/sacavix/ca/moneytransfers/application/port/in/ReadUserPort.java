package com.sacavix.ca.moneytransfers.application.port.in;

import com.sacavix.ca.moneytransfers.domain.User;

public interface ReadUserPort {
    public User read(String username);
}
