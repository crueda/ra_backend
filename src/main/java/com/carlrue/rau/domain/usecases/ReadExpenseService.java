package com.carlrue.rau.domain.usecases;

import com.carlrue.rau.common.UseCase;
import com.carlrue.rau.domain.entities.Expense;
import com.carlrue.rau.domain.entities.User;
import com.carlrue.rau.ports.in.ReadExpensePort;
import com.carlrue.rau.ports.out.LoadExpensePort;

import javax.transaction.Transactional;
import java.util.List;

@UseCase
public class ReadExpenseService implements ReadExpensePort {

    private final LoadExpensePort loadExpensePort;

    public ReadExpenseService(LoadExpensePort loadExpensePort) {
        this.loadExpensePort = loadExpensePort;
    }

    @Transactional
    @Override
    public Expense read(Long id) {

        return loadExpensePort.load(id);
    }

    @Transactional
    @Override
    public List<Expense> readAll() {

        return loadExpensePort.loadAll();

    }

}
