package com.carlrue.rau.domain.usecases;

import com.carlrue.rau.domain.entities.Account;
import com.carlrue.rau.ports.in.BalancePort;
import com.carlrue.rau.ports.out.LoadAccountPort;
import com.carlrue.rau.ports.out.UpdateAccountPort;
import com.carlrue.rau.common.UseCase;

import javax.transaction.Transactional;

@UseCase
public class BalanceService implements BalancePort {

    private final LoadAccountPort loadAccountPort;
    private final UpdateAccountPort updateAccountPort;

    public BalanceService(LoadAccountPort loadAccountPort, UpdateAccountPort updateAccountPort) {
        this.loadAccountPort = loadAccountPort;
        this.updateAccountPort = updateAccountPort;
    }

    @Transactional
    @Override
    public boolean calculate() {


        return true;
    }
}
