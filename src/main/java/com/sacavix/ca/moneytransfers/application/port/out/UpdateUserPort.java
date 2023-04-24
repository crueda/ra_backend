package com.sacavix.ca.moneytransfers.application.port.out;

import com.sacavix.ca.moneytransfers.domain.User;

public interface UpdateUserPort {

    void save(User user);
    void update(User user);

    void delete(Long id);
}
