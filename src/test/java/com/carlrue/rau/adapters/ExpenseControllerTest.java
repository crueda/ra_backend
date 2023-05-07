package com.carlrue.rau.adapters;

import com.carlrue.rau.adapters.in.api.ExpenseController;
import com.carlrue.rau.adapters.out.persistence.ExpenseEntity;
import com.carlrue.rau.adapters.out.persistence.ExpenseMapper;
import com.carlrue.rau.adapters.out.persistence.UserEntity;
import com.carlrue.rau.common.exception.ResourceNotFoundException;
import com.carlrue.rau.domain.entities.Expense;
import com.carlrue.rau.domain.usecases.ReadExpenseService;
import com.carlrue.rau.domain.usecases.SaveExpenseService;
import com.carlrue.rau.ports.in.SaveExpenseCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureJsonTesters
@WebMvcTest(ExpenseController.class)
class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReadExpenseService readExpenseService;
    @MockBean
    private SaveExpenseService saveExpenseService;

    private List<ExpenseEntity> expectedExpenseList;

    @Autowired
    private JacksonTester<ExpenseEntity> jsonExpense;

    @BeforeEach
    void setUp() {
        this.expectedExpenseList = new ArrayList<>();
        this.expectedExpenseList.add(new ExpenseEntity(1L, 1L, BigDecimal.valueOf(12), "Compra en el supermercado", 1683097284869L));
        this.expectedExpenseList.add(new ExpenseEntity(2L, 1L, BigDecimal.valueOf(20), "Pago adicional", 1683099313859L));

    }


    @Test
    void givenExpenseIdThenReturnsExpenseData() throws Exception {
        // Given
        long id = 1L;
        doReturn(ExpenseMapper.entityToDomain(this.expectedExpenseList.get(0))).when(readExpenseService).read(id);

        // When
        MockHttpServletResponse response = mockMvc.perform(get("/api/expense/{id}", id)
                    .accept(MediaType.APPLICATION_JSON)
                )
                .andReturn().getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(jsonExpense.write(this.expectedExpenseList.get(0)).getJson(), response.getContentAsString());
    }

    @Test
    void givenNotExistingExpenseIdThenReturns404() throws Exception {
        long id = 4L;
        when(readExpenseService.read(id)).thenThrow(ResourceNotFoundException.class);

        MockHttpServletResponse response = mockMvc.perform(get("/api/expense/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andReturn().getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void getAllExpensesData() throws Exception {
        doReturn(this.expectedExpenseList).when(readExpenseService).readAll();

        MockHttpServletResponse response = mockMvc.perform(get("/api/expenses")
                    .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertTrue(response.getContentAsString().contains(jsonExpense.write(this.expectedExpenseList.get(0)).getJson()));
        assertTrue(response.getContentAsString().contains(jsonExpense.write(this.expectedExpenseList.get(1)).getJson()));
    }

    @Test
    void createdNewExpenseReturnsTrue() throws Exception {
        ExpenseEntity newExpense = new ExpenseEntity(3L, 1L, BigDecimal.valueOf(40), "Otro pago adicional", 1683099313859L);
        SaveExpenseCommand command = new SaveExpenseCommand(null,
                newExpense.getUserId(),
                newExpense.getAmount(),
                newExpense.getDescription(),
                newExpense.getTimestamp());
        doReturn(true).when(saveExpenseService).save(command);

        MockHttpServletResponse response = mockMvc.perform(post("/api/expense")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(jsonExpense.write(newExpense).getJson())
                )
                .andReturn().getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void givenUpdatedExpenseThenReturnsTrue() throws Exception {
        long id = 1L;
        ExpenseEntity updatedExpense = this.expectedExpenseList.get(0);
        updatedExpense.setDescription("Cambio en la descripción del gasto");
        SaveExpenseCommand command = new SaveExpenseCommand(id,
                updatedExpense.getUserId(),
                updatedExpense.getAmount(),
                updatedExpense.getDescription(),
                updatedExpense.getTimestamp());
        doReturn(true).when(saveExpenseService).save(command);

        MockHttpServletResponse response = mockMvc
                .perform(put("/api/expense")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonExpense.write(updatedExpense).getJson())
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
                .andReturn().getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

}