package com.sacavix.ca.moneytransfers.ports.in;

public interface SaveUserPort {
    public boolean save(SaveUserCommand command);
    public boolean update(SaveUserCommand command);
    public boolean delete(Long id);
}
