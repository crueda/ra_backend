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
        this.extra.save(new ExpenseEntity(null, (long)1L, new BigDecimal(234.23), new String("Comida en restaurante"), (long)1682937240000L));
        this.extra.save(new ExpenseEntity(null, (long)1L, new BigDecimal(54.10),  new String("Compra del supermercado"), (long)1682947320000L));
        this.extra.save(new ExpenseEntity(null, (long)2L, new BigDecimal(722.00),  new String("Alojamiento en la casa rural"), (long)1682953920000L));
        this.extra.save(new ExpenseEntity(null, (long)3L, new BigDecimal(94.70),  new String("Regalos"), (long)1683010980000L));
        this.extra.save(new ExpenseEntity(null, (long)4L, new BigDecimal(122.50),  new String("Viaje de vuelta"), (long)1683026940000L));
    }
}