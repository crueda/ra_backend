package com.sacavix.ca.moneytransfers.adapters.out.persistence;

import com.sacavix.ca.moneytransfers.ports.out.LoadAccountPort;
import com.sacavix.ca.moneytransfers.ports.out.UpdateAccountPort;
import com.sacavix.ca.moneytransfers.common.PersistenceAdapter;
import com.sacavix.ca.moneytransfers.domain.entities.Account;

@PersistenceAdapter
public class AccountPersistenceAdapter implements LoadAccountPort, UpdateAccountPort {

    private final SpringAccountRepository accountRepository;

    public AccountPersistenceAdapter(SpringAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account load(Long id) {
        return accountRepository
                .findById(id)
                .map(AccountMapper::entityToDomain)
                .orElseThrow(RuntimeException::new); // mejorar exception
    }

    @Override
    public void update(Account account) {
        accountRepository.save(AccountMapper.domainToEntity(account));
    }
}
