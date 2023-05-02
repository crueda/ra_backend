package com.carlrue.rau.domain.usecases;

import com.carlrue.rau.domain.entities.Balance;
import com.carlrue.rau.domain.entities.Expense;
import com.carlrue.rau.ports.in.BalancePort;
import com.carlrue.rau.common.UseCase;
import com.carlrue.rau.ports.out.LoadExpensePort;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UseCase
public class BalanceService implements BalancePort {

    private final LoadExpensePort loadExpensePort;

    public BalanceService(LoadExpensePort loadExpensePort) {
        this.loadExpensePort = loadExpensePort;
    }


    @Transactional
    @Override
    public List<Balance> calculate() {

        List<Expense> allExpenses = loadExpensePort.loadAll();
        if (allExpenses.isEmpty()) return new ArrayList<>();

        // Calculate which amount of money each person owes
        BigDecimal totalPerPerson = (allExpenses.stream()
                .map(Expense::getAmount)
                .collect(Collectors.toList())
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add))
                .divide(BigDecimal.valueOf(allExpenses.size()), 2, RoundingMode.HALF_UP);

        // Calculate total amount each person
        Map<String, BigDecimal> expensesPersonDict = new HashMap<String, BigDecimal>();
        for (Expense expense : allExpenses) {
            String dictKey = String.valueOf(expense.getUserId());
            if (expensesPersonDict.containsKey(dictKey)) {
                expensesPersonDict.put(dictKey, expensesPersonDict.get(dictKey).add(expense.getAmount()));
            } else {
                expensesPersonDict.put(dictKey, expense.getAmount());
            }
        }

        List<Balance> balanceList = new ArrayList<>();
        for (Map.Entry<String, BigDecimal> entry : expensesPersonDict.entrySet()) {
            String key = entry.getKey();
            BigDecimal value = entry.getValue();

            Balance balance = new Balance();
            balance.setUserId(Long.parseLong(key));
            balance.setAmount(value.subtract(totalPerPerson));
            balanceList.add(balance);
        }
        return balanceList;

    }
}
