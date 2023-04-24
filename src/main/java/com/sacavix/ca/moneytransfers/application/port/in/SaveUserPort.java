package com.sacavix.ca.moneytransfers.application.port.in;

public interface SaveUserPort {
    public boolean save(SaveUserCommand command);
    public boolean update(SaveUserCommand command);
}
