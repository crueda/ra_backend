package com.carlrue.rau.ports.out;

import com.carlrue.rau.domain.entities.Expense;

import java.util.List;

public interface LoadExpensePort {
    List<Expense> loadAll();
}
