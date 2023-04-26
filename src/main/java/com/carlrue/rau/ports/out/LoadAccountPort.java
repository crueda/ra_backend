package com.carlrue.rau.ports.out;

import com.carlrue.rau.domain.entities.Account;

public interface LoadAccountPort {
    Account load(Long id);
}
