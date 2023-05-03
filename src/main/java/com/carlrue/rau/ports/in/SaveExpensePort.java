package com.carlrue.rau.ports.in;

public interface SaveExpensePort {
    public boolean save(SaveExpenseCommand command);
    public boolean update(SaveExpenseCommand command);
    public boolean delete(Long id);
    public boolean deleteAll();
}
