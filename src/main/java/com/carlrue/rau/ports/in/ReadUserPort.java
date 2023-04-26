package com.carlrue.rau.ports.in;

import com.carlrue.rau.domain.entities.User;
import java.util.List;

public interface ReadUserPort {
    public User read(Long id);
    public List<User> readAll();
}
