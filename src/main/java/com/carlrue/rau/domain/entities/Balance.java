package com.carlrue.rau.domain.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Balance {

    private Long userId;
    private BigDecimal amount;

}
