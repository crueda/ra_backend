package com.sacavix.ca.moneytransfers;

import com.sacavix.ca.moneytransfers.adapter.out.persistence.AccountEntity;
import com.sacavix.ca.moneytransfers.adapter.out.persistence.SpringAccountRepository;
import com.sacavix.ca.moneytransfers.adapter.out.persistence.UserEntity;
import com.sacavix.ca.moneytransfers.adapter.out.persistence.SpringUserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Extra implements InitializingBean {

    private final SpringAccountRepository extra;
    private final SpringUserRepository extra2;

    public Extra(SpringAccountRepository extra, SpringUserRepository extra2) {

        this.extra = extra;
        this.extra2 = extra2;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.extra.save(new AccountEntity(1L, BigDecimal.valueOf(20)));
        this.extra.save(new AccountEntity(2L, BigDecimal.valueOf(25)));
        this.extra2.save(new UserEntity(10L, "aa", "bb", "cc"));
        this.extra2.save(new UserEntity(11L, "aa2", "bb", "cc"));
        this.extra2.save(new UserEntity(12L, "aa3", "bb", "cc"));
    }
}
