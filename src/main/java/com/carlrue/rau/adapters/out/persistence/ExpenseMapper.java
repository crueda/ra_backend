package com.carlrue.rau.adapters.out.persistence;

import com.carlrue.rau.domain.entities.Expense;

public class ExpenseMapper {

    public static Expense entityToDomain(ExpenseEntity expenseEntity) {
        Expense expense = new Expense();
        expense.setId(expenseEntity.getId());
        expense.setUserId(expenseEntity.getUserId());
        expense.setAmount(expenseEntity.getAmount());
        expense.setDescription(expenseEntity.getDescription());
        expense.setTimestamp(expenseEntity.getTimestamp());
        return expense;
    }

    public static ExpenseEntity domainToEntity(Expense expense) {
        ExpenseEntity expenseEntity = new ExpenseEntity();
        expenseEntity.setId(expense.getId());
        expenseEntity.setUserId(expense.getUserId());
        expenseEntity.setAmount(expense.getAmount());
        expenseEntity.setDescription(expense.getDescription());
        expenseEntity.setTimestamp(expense.getTimestamp());
        return expenseEntity;
    }
}
