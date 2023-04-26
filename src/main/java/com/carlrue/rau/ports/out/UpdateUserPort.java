package com.carlrue.rau.ports.out;

import com.carlrue.rau.domain.entities.User;

public interface UpdateUserPort {

    void save(User user);
    void update(User user);

    void delete(Long id);
}
