package com.carlrue.rau.adapters.in.api;

import com.carlrue.rau.ports.in.BalancePort;
import com.carlrue.rau.common.WebAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
public class BalanceController {

    private final BalancePort balancePort;

    public BalanceController(BalancePort balancePort) {
        this.balancePort = balancePort;
    }

/*    @GetMapping(path = "/api/balance")
    void getBalance(

        balancePort.calculate();
    }
*/

}
