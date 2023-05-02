package com.carlrue.rau.adapters.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "expenses")
public class ExpenseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private BigDecimal amount;
    private String description;
    private Long timestamp;

}
