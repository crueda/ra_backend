package com.sacavix.ca.moneytransfers.ports.in;

public interface SendMoneyPort {
    public boolean send(SendMoneyCommand command);
}
