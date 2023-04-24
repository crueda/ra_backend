package com.sacavix.ca.moneytransfers;

import com.sacavix.ca.moneytransfers.adapter.out.persistence.SpringAccountRepository;
import com.sacavix.ca.moneytransfers.adapter.out.persistence.AccountEntity;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InitAmount implements InitializingBean {

    private final SpringAccountRepository extra;

    public InitAmount(SpringAccountRepository extra) {

        this.extra = extra;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.extra.save(new AccountEntity(1L, BigDecimal.valueOf(20)));
        this.extra.save(new AccountEntity(2L, BigDecimal.valueOf(25)));
    }
}
