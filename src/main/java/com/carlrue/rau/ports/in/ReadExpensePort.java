package com.carlrue.rau.ports.in;

import com.carlrue.rau.domain.entities.Expense;

import java.util.List;

public interface ReadExpensePort {
    public List<Expense> readAll();
}
