package com.carlrue.rau.adapters.in.api;

import com.carlrue.rau.ports.in.SendMoneyCommand;
import com.carlrue.rau.ports.in.SendMoneyPort;
import com.carlrue.rau.common.WebAdapter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@WebAdapter
@RestController
public class MoneyTransferController {

    private final SendMoneyPort sendMoneyPort;

    public MoneyTransferController(SendMoneyPort sendMoneyPort) {
        this.sendMoneyPort = sendMoneyPort;
    }

    @PostMapping(path = "/api/accounts/transfer/{sourceAccountId}/{targetAccountId}/{amount}")
    void transfer(
            @PathVariable("sourceAccountId") Long sourceAccountId,
            @PathVariable("targetAccountId") Long targetAccountId,
            @PathVariable("amount") BigDecimal amount) {

        SendMoneyCommand command = new SendMoneyCommand(
                sourceAccountId,
                targetAccountId,
                amount);

        sendMoneyPort.send(command);
    }


}
