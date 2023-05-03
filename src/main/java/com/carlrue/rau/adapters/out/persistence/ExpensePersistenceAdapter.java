package com.carlrue.rau.adapters.out.persistence;

import com.carlrue.rau.common.PersistenceAdapter;
import com.carlrue.rau.common.exception.ResourceNotFoundException;
import com.carlrue.rau.domain.entities.Expense;
import com.carlrue.rau.ports.out.LoadExpensePort;
import com.carlrue.rau.ports.out.UpdateExpensePort;

import java.util.ArrayList;
import java.util.List;

@PersistenceAdapter
public class ExpensePersistenceAdapter implements LoadExpensePort, UpdateExpensePort {

    private final SpringExpenseRepository expenseRepository;

    public ExpensePersistenceAdapter(SpringExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }


    @Override
    public List<Expense> loadAll() {
        List<ExpenseEntity> expensesDB = expenseRepository.findAll();

        List<Expense> expenses = new ArrayList<>();
        for (ExpenseEntity expenseDB : expensesDB) {
            expenses.add(ExpenseMapper.entityToDomain(expenseDB));
        }

        return expenses;
    }

    @Override
    public void update(Expense expense) {

        expenseRepository.save(ExpenseMapper.domainToEntity(expense));
    }

    @Override
    public void save(Expense expense) {

        expenseRepository.save(ExpenseMapper.domainToEntity(expense));
    }

    @Override
    public void delete(Long id) {
        ExpenseEntity expense = expenseRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
        expenseRepository.delete(expense);
    }

    @Override
    public void deleteAll() {
        expenseRepository.deleteAll();
    }

}
