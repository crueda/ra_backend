package com.carlrue.rau.ports.out;

import com.carlrue.rau.domain.entities.Expense;

public interface UpdateExpensePort {

    boolean save(Expense expense);
    boolean update(Expense expense);

    boolean delete(Long id);
    boolean deleteAll();
}
