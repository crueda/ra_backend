package com.sacavix.ca.moneytransfers.adapter.out.persistence;

import com.sacavix.ca.moneytransfers.application.port.out.LoadUserPort;
import com.sacavix.ca.moneytransfers.application.port.out.UpdateUserPort;
import com.sacavix.ca.moneytransfers.common.PersistenceAdapter;
import com.sacavix.ca.moneytransfers.domain.User;

import java.util.ArrayList;
import java.util.List;

@PersistenceAdapter
public class UserPersistenceAdapter implements LoadUserPort, UpdateUserPort {

    private final SpringUserRepository userRepository;

    public UserPersistenceAdapter(SpringUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User load(Long id) {
        return userRepository
                .findById(id)
                .map(UserMapper::entityToDomain)
                .orElseThrow(RuntimeException::new); // TODO: improve exception
    }

    @Override
    public List<User> loadAll() {
        List<UserEntity> usersDB = userRepository.findAll();

        List<User> users = new ArrayList<>();
        for (UserEntity userDB : usersDB) {
            users.add(UserMapper.entityToDomain(userDB));
        }

        return users;
    }

    @Override
    public void update(User user) {

        userRepository.save(UserMapper.domainToEntity(user));
    }

    @Override
    public void save(User user) {
        userRepository.save(UserMapper.domainToEntity(user));
    }

    @Override
    public void delete(Long id) {
        UserEntity user = userRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
        userRepository.delete(user);
    }
}
