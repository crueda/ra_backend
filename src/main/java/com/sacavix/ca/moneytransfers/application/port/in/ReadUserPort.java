package com.sacavix.ca.moneytransfers.application.port.in;

import com.sacavix.ca.moneytransfers.domain.User;
import java.util.List;

public interface ReadUserPort {
    public User read(Long id);
    public List<User> readAll();
}
