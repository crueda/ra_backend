package com.carlrue.rau.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Expense {

    private Long id;
    private Long userId;
    private BigDecimal amount;
    private String description;
    private Long timestamp;

    public boolean expenseIsValid() {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }
}
