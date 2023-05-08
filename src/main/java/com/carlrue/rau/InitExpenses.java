package com.carlrue.rau;

import com.carlrue.rau.adapters.out.persistence.SpringExpenseRepository;
import com.carlrue.rau.adapters.out.persistence.ExpenseEntity;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InitExpenses implements InitializingBean {

    private final SpringExpenseRepository extra;

    public InitExpenses(SpringExpenseRepository extra) {

        this.extra = extra;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.extra.save(new ExpenseEntity(null, (long)1L, BigDecimal.valueOf(234.23), "Comida en restaurante", (long)1683337240000L));
        this.extra.save(new ExpenseEntity(null, (long)1L, BigDecimal.valueOf(54.10),  "Compra del supermercado", (long)1683347320000L));
        this.extra.save(new ExpenseEntity(null, (long)2L, BigDecimal.valueOf(722.00),  "Alojamiento en la casa rural", (long)1683353920000L));
        this.extra.save(new ExpenseEntity(null, (long)3L, BigDecimal.valueOf(94.70),  "Regalos", (long)1683310980000L));
        this.extra.save(new ExpenseEntity(null, (long)4L, BigDecimal.valueOf(122.50),  "Viaje de vuelta", (long)1683326940000L));
    }
}
