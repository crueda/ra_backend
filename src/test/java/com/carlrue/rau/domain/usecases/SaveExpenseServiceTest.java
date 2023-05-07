package com.carlrue.rau.domain.usecases;


import com.carlrue.rau.common.exception.ResourceNotFoundException;
import com.carlrue.rau.domain.entities.Expense;
import com.carlrue.rau.ports.in.SaveExpenseCommand;
import com.carlrue.rau.ports.out.LoadExpensePort;
import com.carlrue.rau.ports.out.UpdateExpensePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveExpenseServiceTest {

    @Mock
    private LoadExpensePort loadExpensePort;

    @Mock
    private UpdateExpensePort updateExpensePort;

    private SaveExpenseService saveExpenseService;
    private List<Expense> expectedExpenseList;

    @BeforeEach
    void setUp() {
        this.saveExpenseService = new SaveExpenseService(loadExpensePort, updateExpensePort);
        this.expectedExpenseList = new ArrayList<>();
        this.expectedExpenseList.add(new Expense(1L, 1L, BigDecimal.valueOf(12), "Compra en el supermercado", 1683097284869L));
        this.expectedExpenseList.add(new Expense(2L, 1L, BigDecimal.valueOf(20), "Pago adicional", 1683099313859L));
    }

    @Test
    void createExpenseWhenExpenseIsProvided() {
        Expense expectedNewExpense = new Expense(null,1L, BigDecimal.valueOf(12), "Compra en el supermercado", 1683097284869L);
        SaveExpenseCommand command = new SaveExpenseCommand(null, 1L, BigDecimal.valueOf(12), "Compra en el supermercado", 1683097284869L);
        doReturn(true).when(updateExpensePort).save(expectedNewExpense);

        boolean result = saveExpenseService.save(command);

        assertEquals(result, true);
    }

    @Test
    void updateExpenseWhenIsEdited() {
        Expense expectedUpdateExpense = new Expense(1L, 1L, BigDecimal.valueOf(12), "Compra en el supermercado", 1683097284869L);
        SaveExpenseCommand command = new SaveExpenseCommand(1L, 1L, BigDecimal.valueOf(12), "Compra en el supermercado", 1683097284869L);
        doReturn(true).when(updateExpensePort).update(expectedUpdateExpense);

        boolean result = saveExpenseService.update(command);

        assertEquals(result, true);
    }


    @Test
    void deleteExpenseById() {
        Expense optExpectedExpense = expectedExpenseList.get(0);
        doReturn(optExpectedExpense).when(loadExpensePort).load(1L);

        assertEquals(saveExpenseService.delete(optExpectedExpense.getId()), true);
    }

    @Test
    void tryingToDeleteNotExistingExpenseIdThenReturnsResourceNotFoundException() {
        long id = 6L;
        when(loadExpensePort.load(id)).thenThrow(ResourceNotFoundException.class);

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> when(saveExpenseService.delete(id)),
                "ResourceNotFoundException was expected"
        );
    }


}
