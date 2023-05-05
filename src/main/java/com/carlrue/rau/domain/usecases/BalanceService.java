package com.carlrue.rau.domain.usecases;

import com.carlrue.rau.domain.entities.Balance;
import com.carlrue.rau.domain.entities.Expense;
import com.carlrue.rau.domain.entities.User;
import com.carlrue.rau.ports.in.BalancePort;
import com.carlrue.rau.common.UseCase;
import com.carlrue.rau.ports.out.LoadExpensePort;
import com.carlrue.rau.ports.out.LoadUserPort;

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

    private final LoadUserPort loadUserPort;

    public BalanceService(LoadExpensePort loadExpensePort, LoadUserPort loadUserPort) {
        this.loadExpensePort = loadExpensePort;
        this.loadUserPort = loadUserPort;
    }


    @Transactional
    @Override
    public List<Balance> calculate() {

        List<Expense> allExpenses = loadExpensePort.loadAll();
        if (allExpenses.isEmpty()) return new ArrayList<>();

        List<User> allUsers = loadUserPort.loadAll();
        BigDecimal numUsers = new BigDecimal(allUsers.size());

        // Calculate which amount of money each person owes
        BigDecimal totalAmount = (allExpenses.stream()
                .map(Expense::getAmount)
                .collect(Collectors.toList())
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add));


        // Calculate total amount each person
        Map<String, BigDecimal> expensesPersonDict = new HashMap<>();
        for (Expense expense : allExpenses) {
            String dictKey = String.valueOf(expense.getUserId());
            if (expensesPersonDict.containsKey(dictKey)) {
                expensesPersonDict.put(dictKey, expensesPersonDict.get(dictKey).add(expense.getAmount()));
            } else {
                expensesPersonDict.put(dictKey, expense.getAmount());
            }
        }

        List<Balance> balanceList = new ArrayList<>();
        for (User entryUser : allUsers) {
            String key = String.valueOf(entryUser.getId());
            BigDecimal value;
            if (expensesPersonDict.containsKey(key)) {
                value = expensesPersonDict.get(key);
            } else {
                value = new BigDecimal(0);
            }

            Balance balance = new Balance();
            balance.setUserId(Long.parseLong(key));
            BigDecimal totalPerUser = totalAmount.divide(numUsers, RoundingMode.HALF_UP);
            balance.setAmount(value.subtract(totalPerUser));
            balanceList.add(balance);
        }
        return balanceList;
    }
}
