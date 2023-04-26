package com.carlrue.rau.ports.in;

public interface SendMoneyPort {
    public boolean send(SendMoneyCommand command);
}
