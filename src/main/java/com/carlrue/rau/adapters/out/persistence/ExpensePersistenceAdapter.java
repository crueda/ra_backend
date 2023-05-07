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
    public Expense load(Long id) {
        return expenseRepository
                .findById(id)
                .map(ExpenseMapper::entityToDomain)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Expense", "Id", id)
                );
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
    public boolean update(Expense expense) {

        expenseRepository.save(ExpenseMapper.domainToEntity(expense));

        return true;
    }

    @Override
    public boolean save(Expense expense) {

        expenseRepository.save(ExpenseMapper.domainToEntity(expense));

        return true;
    }

    @Override
    public boolean delete(Long id) {
        Expense expenseToDelete = expenseRepository
                .findById(id)
                .map(ExpenseMapper::entityToDomain)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Expense", "Id", id)
                );
        expenseRepository.delete(ExpenseMapper.domainToEntity(expenseToDelete));

        return true;
    }

    @Override
    public boolean deleteAll() {
        expenseRepository.deleteAll();

        return true;
    }

}
