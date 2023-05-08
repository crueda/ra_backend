package com.carlrue.rau.domain.usecases;

import com.carlrue.rau.common.UseCase;
import com.carlrue.rau.common.exception.ResourceInvalidException;
import com.carlrue.rau.domain.entities.Expense;
import com.carlrue.rau.domain.entities.User;
import com.carlrue.rau.ports.in.SaveExpenseCommand;
import com.carlrue.rau.ports.in.SaveExpensePort;
import com.carlrue.rau.ports.out.LoadExpensePort;
import com.carlrue.rau.ports.out.UpdateExpensePort;

import javax.transaction.Transactional;

@UseCase
public class SaveExpenseService implements SaveExpensePort {

    private final LoadExpensePort loadExpensePort;
    private final UpdateExpensePort updateExpensePort;

    public SaveExpenseService(LoadExpensePort loadExpensePort, UpdateExpensePort updateExpensePort) {
        this.loadExpensePort = loadExpensePort;
        this.updateExpensePort = updateExpensePort;
    }

    @Transactional
    @Override
    public boolean save(SaveExpenseCommand command) {

        Expense expense = new Expense();
        expense.setUserId(command.getUserId());
        expense.setAmount(command.getAmount());
        expense.setDescription(command.getDescription());
        expense.setTimestamp(command.getTimestamp());

        if (!expense.expenseIsValid()) {
            throw new ResourceInvalidException("User", "Expense", expense.getAmount());
        }

        updateExpensePort.save(expense);

        return true;
    }

    @Transactional
    @Override
    public boolean update(SaveExpenseCommand command) {

        Expense expense = new Expense();
        expense.setId(command.getId());
        expense.setUserId(command.getUserId());
        expense.setAmount(command.getAmount());
        expense.setDescription(command.getDescription());
        expense.setTimestamp(command.getTimestamp());

        if (!expense.expenseIsValid()) {
            throw new ResourceInvalidException("User", "Expense", expense.getAmount());
        }

        updateExpensePort.update(expense);

        return true;
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        Expense expense = loadExpensePort.load(id);

        updateExpensePort.delete(expense.getId());

        return true;
    }

    @Transactional
    @Override
    public boolean deleteAll() {

        updateExpensePort.deleteAll();

        return true;
    }

}
