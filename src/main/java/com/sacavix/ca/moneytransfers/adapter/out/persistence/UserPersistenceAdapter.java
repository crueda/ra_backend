package com.sacavix.ca.moneytransfers.adapter.out.persistence;

import com.sacavix.ca.moneytransfers.application.port.out.LoadUserPort;
import com.sacavix.ca.moneytransfers.application.port.out.UpdateUserPort;
import com.sacavix.ca.moneytransfers.common.PersistenceAdapter;
import com.sacavix.ca.moneytransfers.domain.User;

@PersistenceAdapter
public class UserPersistenceAdapter implements LoadUserPort, UpdateUserPort {

    private final SpringUserRepository userRepository;

    public UserPersistenceAdapter(SpringUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User load(String username) {
        Long id = 3L;
        return userRepository
                .findById(id)
                .map(UserMapper::entityToDomain)
                .orElseThrow(RuntimeException::new); // TODO: improve exception
    }

    @Override
    public void update(User user) {
        userRepository.save(UserMapper.domainToEntity(user));
    }
}
