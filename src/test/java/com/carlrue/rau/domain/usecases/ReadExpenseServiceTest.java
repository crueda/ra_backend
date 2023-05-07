package com.carlrue.rau.domain.usecases;


import com.carlrue.rau.adapters.out.persistence.ExpenseEntity;
import com.carlrue.rau.common.exception.ResourceNotFoundException;
import com.carlrue.rau.domain.entities.Expense;
import com.carlrue.rau.domain.entities.User;
import com.carlrue.rau.ports.out.LoadExpensePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReadExpenseServiceTest {

    @Mock
    private LoadExpensePort loadExpensePort;

    private ReadExpenseService readExpenseService;
    private List<Expense> expectedExpenseList;

    @BeforeEach
    void setUp() {
        this.readExpenseService = new ReadExpenseService(loadExpensePort);
        this.expectedExpenseList = new ArrayList<>();
        this.expectedExpenseList.add(new Expense(1L, 1L, BigDecimal.valueOf(12), "Compra en el supermercado", 1683097284869L));
        this.expectedExpenseList.add(new Expense(2L, 1L, BigDecimal.valueOf(20), "Pago adicional", 1683099313859L));
    }
    @Test
    void givenExpenseIdThenReturnsUser() {
        // Given
        Expense optExpectedExpense = expectedExpenseList.get(0);
        doReturn(optExpectedExpense).when(loadExpensePort).load(1L);

        // When
        Expense expense = readExpenseService.read(1L);

        // Then
        assertNotNull(expense);
        assertEquals(1L, expense.getId());
        assertEquals("Compra en el supermercado", expense.getDescription());
    }


    @Test
    void givenNotExistingExpenseIdThenReturnsResourceNotFoundException() {
        long id = 6L;
        when(loadExpensePort.load(id)).thenThrow(ResourceNotFoundException.class);

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> when(readExpenseService.read(id)),
                "ResourceNotFoundException was expected"
        );
    }

    @Test
    void getAllExpenses() {
        doReturn(this.expectedExpenseList).when(loadExpensePort).loadAll();
        // When
        List<Expense> expenseList = readExpenseService.readAll();
        // Then
        assertNotNull(expenseList);
        assertEquals(expenseList.size(), 2);
        assertEquals(1L, expenseList.get(0).getId());
        assertEquals(2L, expenseList.get(1).getId());
    }

    @Test
    void getEmptyListWhenThereIsNoExpenses() {
        List<Expense> expectedExpenseList = new ArrayList<>();
        doReturn(expectedExpenseList).when(loadExpensePort).loadAll();

        // When
        List<Expense> expenseList = loadExpensePort.loadAll();

        // Then
        assertNotNull(expenseList);
        assertTrue(expenseList.isEmpty());
    }

}
