package com.carlrue.rau.domain.usecases;

import com.carlrue.rau.domain.entities.Account;
import com.carlrue.rau.ports.in.SendMoneyCommand;
import com.carlrue.rau.ports.in.SendMoneyPort;
import com.carlrue.rau.ports.out.LoadAccountPort;
import com.carlrue.rau.ports.out.UpdateAccountPort;
import com.carlrue.rau.common.UseCase;

import javax.transaction.Transactional;

@UseCase
public class SendMoneyService implements SendMoneyPort {

    private final LoadAccountPort loadAccountPort;
    private final UpdateAccountPort updateAccountPort;

    public SendMoneyService(LoadAccountPort loadAccountPort, UpdateAccountPort updateAccountPort) {
        this.loadAccountPort = loadAccountPort;
        this.updateAccountPort = updateAccountPort;
    }

    @Transactional
    @Override
    public boolean send(SendMoneyCommand command) {

        Account source = loadAccountPort.load(command.getSourceId());
        Account target = loadAccountPort.load(command.getTargetId());

        if(!source.isBalanceGreaterThan(command.getAmount())) {
            throw new RuntimeException("Source account not have enough balance amount ... ");
        }

        target.plus(command.getAmount());
        source.subtract(command.getAmount());

        updateAccountPort.update(source);
        updateAccountPort.update(target);

        return true;
    }
}
