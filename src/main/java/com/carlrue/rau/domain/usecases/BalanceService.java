package com.carlrue.rau.domain.usecases;

import com.carlrue.rau.ports.in.BalancePort;
import com.carlrue.rau.common.UseCase;

import javax.transaction.Transactional;

@UseCase
public class BalanceService implements BalancePort {


    public BalanceService() {
    }

    @Transactional
    @Override
    public boolean calculate() {


        return true;
    }
}
