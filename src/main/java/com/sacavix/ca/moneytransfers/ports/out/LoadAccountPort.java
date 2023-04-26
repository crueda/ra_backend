package com.sacavix.ca.moneytransfers.ports.out;

import com.sacavix.ca.moneytransfers.domain.entities.Account;

public interface LoadAccountPort {
    Account load(Long id);
}
