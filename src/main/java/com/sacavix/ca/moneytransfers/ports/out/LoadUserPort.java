package com.sacavix.ca.moneytransfers.ports.out;

import com.sacavix.ca.moneytransfers.domain.entities.User;
import java.util.List;

public interface LoadUserPort {
    User load(Long id);
    List<User> loadAll();
}
