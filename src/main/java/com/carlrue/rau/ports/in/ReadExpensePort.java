package com.carlrue.rau.ports.in;

import com.carlrue.rau.domain.entities.Expense;

import java.util.List;

public interface ReadExpensePort {
    public Expense read(Long id);
    public List<Expense> readAll();
}
