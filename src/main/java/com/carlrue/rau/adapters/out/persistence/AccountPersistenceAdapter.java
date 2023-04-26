package com.carlrue.rau.adapters.out.persistence;

import com.carlrue.rau.ports.out.LoadAccountPort;
import com.carlrue.rau.ports.out.UpdateAccountPort;
import com.carlrue.rau.common.PersistenceAdapter;
import com.carlrue.rau.domain.entities.Account;

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
