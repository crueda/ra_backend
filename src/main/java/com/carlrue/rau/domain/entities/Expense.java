package com.carlrue.rau.domain.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Expense {

    private Long id;
    private Long userId;
    private BigDecimal amount;
    private String description;
    private Long timestamp;

}
