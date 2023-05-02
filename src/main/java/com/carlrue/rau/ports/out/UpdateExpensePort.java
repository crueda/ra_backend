package com.carlrue.rau.ports.out;

import com.carlrue.rau.domain.entities.Expense;

public interface UpdateExpensePort {

    void save(Expense expense);
    void update(Expense expense);

    void delete(Long id);
}
