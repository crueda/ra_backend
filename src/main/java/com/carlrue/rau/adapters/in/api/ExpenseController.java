package com.carlrue.rau.adapters.in.api;

import com.carlrue.rau.adapters.out.persistence.ExpenseEntity;
import com.carlrue.rau.adapters.out.persistence.ExpenseMapper;
import com.carlrue.rau.common.WebAdapter;
import com.carlrue.rau.domain.entities.Expense;
import com.carlrue.rau.ports.in.ReadExpensePort;
import com.carlrue.rau.ports.in.SaveExpenseCommand;
import com.carlrue.rau.ports.in.SaveExpensePort;
import com.carlrue.rau.ports.in.ExpenseRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@WebAdapter
@RestController
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class ExpenseController {

    private final SaveExpensePort saveExpensePort;
    private final ReadExpensePort readExpensePort;


    public ExpenseController(SaveExpensePort saveExpensePort, ReadExpensePort readExpensePort) {

        this.saveExpensePort = saveExpensePort;
        this.readExpensePort = readExpensePort;
    }

    @PostMapping(path = "/api/expense")
    @CrossOrigin(origins = "*")
    @ResponseStatus(code = HttpStatus.CREATED, reason = "CREATED")
    void createExpense(@RequestBody ExpenseRequest expenseRequest) {

        Long userId = (long)expenseRequest.getUserId();
        BigDecimal amount = expenseRequest.getAmount();
        String description = new String(expenseRequest.getDescription());
        Long timestamp = (long)expenseRequest.getTimestamp();

        SaveExpenseCommand command = new SaveExpenseCommand(null,
                userId,
                amount,
                description,
                timestamp);

        saveExpensePort.save(command);
    }

    @PutMapping(path = "/api/expense")
    @CrossOrigin(origins = "*")
    @ResponseStatus(code = HttpStatus.OK, reason = "UPDATED")
    void updateExpense(@RequestBody ExpenseRequest expenseRequest) {

        Long id = (long)expenseRequest.getId();
        Long userId = (long)expenseRequest.getUserId();
        BigDecimal amount = expenseRequest.getAmount();
        String description = new String(expenseRequest.getDescription());
        Long timestamp = (long)expenseRequest.getTimestamp();

        SaveExpenseCommand command = new SaveExpenseCommand(id,
                userId,
                amount,
                description,
                timestamp);

        saveExpensePort.update(command);
    }

    @DeleteMapping(path = "/api/expense")
    @CrossOrigin(origins = "*")
    @ResponseStatus(code = HttpStatus.OK, reason = "DELETED")
    void deleteExpense(@RequestBody ExpenseRequest expenseRequest) {

        Long id = (long)expenseRequest.getId();

        saveExpensePort.delete(id);
    }

    @GetMapping("/api/expense/{id}")
    @CrossOrigin(origins = "*")
    @ResponseStatus(code = HttpStatus.OK)
    public ExpenseEntity readExpense(@PathVariable Long id) {

        Expense expense = readExpensePort.read(id);
        return ExpenseMapper.domainToEntity(expense);
    }

    @GetMapping("/api/expenses")
    @CrossOrigin(origins = "*")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<Expense>> allExpenses() {
        List<Expense> expenses = readExpensePort.readAll();

        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/api/expenses/settle")
    @CrossOrigin(origins = "*")
    @ResponseStatus(code = HttpStatus.OK)
    void deleteAllExpenses() {
        saveExpensePort.deleteAll();
    }
}
