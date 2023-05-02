package com.carlrue.rau.ports.in;

import com.carlrue.rau.domain.entities.Balance;

import java.util.List;

public interface BalancePort {
    public List<Balance> calculate();
}
