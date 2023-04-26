package com.sacavix.ca.moneytransfers.ports.out;

import com.sacavix.ca.moneytransfers.domain.entities.User;

public interface UpdateUserPort {

    void save(User user);
    void update(User user);

    void delete(Long id);
}
