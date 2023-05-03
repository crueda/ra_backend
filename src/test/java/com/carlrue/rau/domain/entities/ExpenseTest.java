package com.carlrue.rau.domain.entities;

import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpenseTest {

    @Test
    public void test_expense_isValid(){
        Expense expense = new Expense(1L, 1L, BigDecimal.valueOf(1), "compra", 0L);
        assertTrue(expense.expenseIsValid());
    }
    @Test
    public void test_expense_isNotValid(){
        Expense expense = new Expense(1L, 1L, BigDecimal.valueOf(-1), "compra", 0L);
        assertFalse(expense.expenseIsValid());
    }
}
