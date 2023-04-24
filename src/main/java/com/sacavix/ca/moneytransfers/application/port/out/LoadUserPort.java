package com.sacavix.ca.moneytransfers.application.port.out;

import com.sacavix.ca.moneytransfers.domain.User;

public interface LoadUserPort {
    User load(Long id);
}
