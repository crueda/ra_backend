package com.carlrue.rau.ports.out;

import com.carlrue.rau.domain.entities.User;

public interface UpdateUserPort {

    boolean save(User user);
    boolean update(User user);

    boolean delete(Long id);
}
