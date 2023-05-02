package com.carlrue.rau.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringExpenseRepository extends JpaRepository<ExpenseEntity, Long> {
}
