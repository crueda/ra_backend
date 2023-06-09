package com.carlrue.rau.ports.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveExpenseCommand {
    private Long id;
    private Long userId;
    private BigDecimal amount;
    private String description;
    private Long timestamp;
}
