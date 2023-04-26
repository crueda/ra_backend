package com.carlrue.rau.ports.out;

import com.carlrue.rau.domain.entities.User;
import java.util.List;

public interface LoadUserPort {
    User load(Long id);
    List<User> loadAll();
}
