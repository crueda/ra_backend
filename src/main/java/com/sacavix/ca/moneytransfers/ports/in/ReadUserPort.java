package com.sacavix.ca.moneytransfers.ports.in;

import com.sacavix.ca.moneytransfers.domain.entities.User;
import java.util.List;

public interface ReadUserPort {
    public User read(Long id);
    public List<User> readAll();
}
