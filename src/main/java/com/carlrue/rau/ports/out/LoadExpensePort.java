package com.carlrue.rau.ports.out;

import com.carlrue.rau.domain.entities.Expense;

import java.util.List;

public interface LoadExpensePort {

    Expense load(Long id);
    List<Expense> loadAll();
}
