package com.sacavix.ca.moneytransfers.application.port.out;

import com.sacavix.ca.moneytransfers.domain.User;
import java.util.List;

public interface LoadUserPort {
    User load(Long id);
    List<User> loadAll();
}
